package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.BillOfMaterialDto;

public interface BillOfMaterialController {
	@GetMapping("/getbillofmaterials")
	ResponseEntity<ResponseDto> getBillOfMaterials();
	@PutMapping("/updatebillofmaterial")
	ResponseEntity<ResponseDto> updateBillOfMaterial(@RequestBody BillOfMaterialDto billOfMaterialDto);
	@GetMapping("/getbillofmaterial/{billofmaterialid}")
	ResponseEntity<ResponseDto> getBillOfMaterial(@PathVariable String billOfMaterialid);
	@PostMapping("/savebillofmaterial")
	ResponseEntity<ResponseDto> saveBillOfMaterial(@RequestBody BillOfMaterialDto billOfMaterialDto);
	@PostMapping("/activeinactivebillofmaterial")
	ResponseEntity<ResponseDto> activeinactiveBillOfMaterial(@RequestBody ActiveInactiveEntityDto aiedto);
	
}
