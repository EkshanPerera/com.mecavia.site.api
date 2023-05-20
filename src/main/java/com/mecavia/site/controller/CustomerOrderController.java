package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.CustomerOrderDto;

public interface CustomerOrderController {
	@GetMapping("/getcustomerorders")
	ResponseEntity<ResponseDto> getCustomerOrders();
	@PutMapping("/updatecustomerorder")
	ResponseEntity<ResponseDto> updateCustomerOrder(@RequestBody CustomerOrderDto customerOrderDto);
	@GetMapping("/getcustomerorder/{customerorderid}")
	ResponseEntity<ResponseDto> getCustomerOrder(@PathVariable String customerOrderid);
	@PostMapping("/savecustomerorder")
	ResponseEntity<ResponseDto> saveCustomerOrder(@RequestBody CustomerOrderDto customerOrderDto);
	@PostMapping("/activeinactivecustomerorder")
	ResponseEntity<ResponseDto> activeinactiveCustomerOrder(@RequestBody ActiveInactiveEntityDto aiedto);
	
}
