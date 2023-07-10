package com.mecavia.site.controllerimplies;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.CustomerOrderController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.CustomerOrderDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.CustomerOrderServiceImpl;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/customerorderctrl")
@CrossOrigin(origins = "*")
public class CustomerOrderControllerImpl implements CustomerOrderController {
	@Autowired
	private CustomerOrderServiceImpl customerOrderservice;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> getCustomerOrders() {
		try {
			List<CustomerOrderDto> CustomerOrders =  customerOrderservice.getCustomerOrders();
			if(CustomerOrders.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(CustomerOrders);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
			responseDto.setMessage("INTERNAL_SERVER_ERROR");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> updateCustomerOrder(CustomerOrderDto CustomerOrderDto) {
		try {
			String res = customerOrderservice.updateCustomerOrder(CustomerOrderDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(CustomerOrderDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("CustomerOrder not found");
				responseDto.setContent(CustomerOrderDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> getCustomerOrder(String CustomerOrderid) {
		try {
			CustomerOrderDto CustomerOrder =  customerOrderservice.getCustomerOrder(CustomerOrderid);
			if(CustomerOrder==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("CustomerOrder not found");
				responseDto.setContent(CustomerOrder);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(CustomerOrder);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
			responseDto.setMessage("no records found");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<ResponseDto> saveCustomerOrder(CustomerOrderDto CustomerOrderDto) {
		try {
			String res = customerOrderservice.saveCustomerOrder(CustomerOrderDto);
			if(res.equals("00")){
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(CustomerOrderDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("06")) {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("CustomerOrder alrady exist");
				responseDto.setContent(CustomerOrderDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("Error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> activeinactiveCustomerOrder(ActiveInactiveEntityDto aiedto){
		try {
			String res = customerOrderservice.activeinactiveCustomerOrder(aiedto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(aiedto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("CustomerOrder not exists");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
