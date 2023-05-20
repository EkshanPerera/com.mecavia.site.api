package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.MaterialRequisitionDto;

public interface MaterialRequisitionController {
	@GetMapping("/getmaterialrequisitions")
	ResponseEntity<ResponseDto> getMaterialRequisitions();
	@PutMapping("/updatematerialrequisition")
	ResponseEntity<ResponseDto> updateMaterialRequisition(@RequestBody MaterialRequisitionDto materialRequisitionDto);
	@GetMapping("/getmaterialrequisition/{materialrequisitionid}")
	ResponseEntity<ResponseDto> getMaterialRequisition(@PathVariable String materialRequisitionid);
	@PostMapping("/savematerialrequisition")
	ResponseEntity<ResponseDto> saveMaterialRequisition(@RequestBody MaterialRequisitionDto materialRequisitionDto);
	@PostMapping("/activeinactivematerialrequisition")
	ResponseEntity<ResponseDto> activeinactiveMaterialRequisition(@RequestBody ActiveInactiveEntityDto aiedto);
}
