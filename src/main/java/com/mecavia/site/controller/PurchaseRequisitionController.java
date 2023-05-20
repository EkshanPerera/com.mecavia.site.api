package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.PurchaseRequisitionDto;

public interface PurchaseRequisitionController {
	@GetMapping("/getpurchaserequisitions")
	ResponseEntity<ResponseDto> getPurchaseRequisitions();
	@PutMapping("/updatepurchaserequisition")
	ResponseEntity<ResponseDto> updatePurchaseRequisition(@RequestBody PurchaseRequisitionDto purchaseRequisitionDto);
	@GetMapping("/getpurchaserequisition/{purchaserequisitionid}")
	ResponseEntity<ResponseDto> getPurchaseRequisition(@PathVariable String purchaseRequisitionid);
	@PostMapping("/savepurchaserequisition")
	ResponseEntity<ResponseDto> savePurchaseRequisition(@RequestBody PurchaseRequisitionDto purchaseRequisitionDto);
	@PostMapping("/activeinactivepurchaserequisition")
	ResponseEntity<ResponseDto> activeinactivePurchaseRequisition(@RequestBody ActiveInactiveEntityDto aiedto);
	
}
