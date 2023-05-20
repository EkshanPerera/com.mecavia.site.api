package com.mecavia.site.controllerimplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.SMSDto;
import com.mecavia.site.serviceimplies.SMSService;
import com.mecavia.site.util.VarList;

@RestController
@CrossOrigin
@RequestMapping("/api/smsctrl")
public class SMSController implements com.mecavia.site.controller.SMSController {
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> sendSMS(SMSDto smsDto) {
		try {
			String res = smsService.sendSMS(smsDto);
			if(res == "00") {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(smsDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else { 
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(smsDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
