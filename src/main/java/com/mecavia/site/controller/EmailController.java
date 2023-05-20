package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.EmailDto;
import com.mecavia.site.dto.ResponseDto;

public interface EmailController {
	@PostMapping("/sendemail")
	ResponseEntity<ResponseDto> sendEmail(@RequestBody EmailDto emailDto);
}
