package com.mecavia.site.service;

import com.mecavia.site.dto.SMSDto;

public interface SMSServive {
	String sendSMS(SMSDto smsDto) throws Exception;
}
