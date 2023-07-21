package com.mecavia.site.serviceimplies;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.EmailDto;
import com.mecavia.site.entity.Email;
import com.mecavia.site.repo.EmailRepo;
import com.mecavia.site.service.EmailService;
import com.mecavia.site.util.EmailApi;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailApi emailApi;
	
	@Autowired
	private EmailRepo emailRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public String sendSMS(EmailDto emailDto)  {
		try {
			emailApi.sendMail(emailDto.getToEmail(), emailDto.getSubject(), emailDto.getBody());
			emailDto.setStatus(Status.DELEVERED);
			emailRepo.save(modelMapper.map(emailDto, Email.class));
			return VarList.RSP_SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return VarList.RSP_ERROR;
		}
	}

}
