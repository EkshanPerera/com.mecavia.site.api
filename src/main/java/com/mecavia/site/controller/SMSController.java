package com.mecavia.site.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.SMSDto;

public interface SMSController {
	@PostMapping("/sendsms")
	ResponseEntity<ResponseDto> sendSMS(@RequestBody SMSDto smsDto);
}
