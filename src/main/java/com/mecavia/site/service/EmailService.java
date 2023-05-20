package com.mecavia.site.service;

import com.mecavia.site.dto.EmailDto;

public interface EmailService {
	String sendSMS(EmailDto emailDto) ;
}
