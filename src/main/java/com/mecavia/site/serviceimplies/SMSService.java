package com.mecavia.site.serviceimplies;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.SMSDto;
import com.mecavia.site.entity.SMS;
import com.mecavia.site.repo.SMSRepo;
import com.mecavia.site.service.SMSServive;
import com.mecavia.site.util.SMSApi;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class SMSService implements SMSServive {
	@Autowired
	private SMSApi smsApi;
	
	@Autowired
	private SMSRepo smsRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String sendSMS(SMSDto smsDto) throws Exception {
		String resp = smsApi.sendSMS(smsDto.getMassage(),smsDto.getReceiver());
		if(resp == "00") {
			smsDto.setStatus(Status.DELEVERED);
			smsRepo.save(modelMapper.map(smsDto, SMS.class));
			return VarList.RSP_SUCCESS;
		}else {
			smsDto.setStatus(Status.FAILD);
			smsRepo.save(modelMapper.map(smsDto, SMS.class));
			return VarList.RSP_ERROR;
		}
		
	}

}
