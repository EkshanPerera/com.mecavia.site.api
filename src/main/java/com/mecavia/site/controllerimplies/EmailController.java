package com.mecavia.site.controllerimplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.EmailDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.EmailService;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping("/api/emailctrl")
@CrossOrigin(origins = "*")
public class EmailController implements com.mecavia.site.controller.EmailController{
	@Autowired	
	private EmailService emailService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> sendEmail(EmailDto emailDto) {
		try {
			String res = emailService.sendSMS(emailDto);
			if (res == "00") {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(emailDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Internal Server error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
