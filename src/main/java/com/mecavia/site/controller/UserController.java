package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.UserDto;

public interface UserController {
	@GetMapping("/getusers")
	ResponseEntity<ResponseDto> getUsers();
	@PutMapping("/updateuser")
	ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto);
	@GetMapping("/getuser/{userid}")
	ResponseEntity<ResponseDto> getUser(@PathVariable String userid);
	@PostMapping("/saveuser")
	ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto userDto);
	@PostMapping("/activeinactiveuser")
	ResponseEntity<ResponseDto> activeinactiveUser(@RequestBody ActiveInactiveEntityDto aiedto);
	
}
