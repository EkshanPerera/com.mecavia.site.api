package com.mecavia.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.AuthenticationRequestDto;
import com.mecavia.site.dto.AuthenticationResponseDto;
import com.mecavia.site.dto.UserDto;
import com.mecavia.site.serviceimplies.UserService;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthContoller {
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authrequest) {
		AuthenticationResponseDto dto = new AuthenticationResponseDto();
		String token;
		try {
			token = userservice.authenticate(authrequest);
			if (!token.equals(null)) {
				dto.setToken(token);
				dto.setCode(VarList.RSP_SUCCESS);
				dto.setMassage("success");
				return new ResponseEntity<AuthenticationResponseDto>(dto,HttpStatus.OK);
			}else {
				dto.setToken(null);
				dto.setCode(VarList.RSP_NOT_AUTHORISED);
				dto.setMassage("token not found");
				return new ResponseEntity<AuthenticationResponseDto>(dto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			dto.setToken(null);
			dto.setCode(VarList.RSP_NOT_AUTHORISED);
			dto.setMassage("invalid email or password");
			return new ResponseEntity<AuthenticationResponseDto>(dto,HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userservice.register(userDto));
	}
	
}
