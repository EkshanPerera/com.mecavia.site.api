package com.mecavia.site.controllerimplies;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.MaterialRequisitionController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialRequisitionDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.MaterialRequisitionServiceImpl;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/materialrequisitionctrl")
@CrossOrigin(origins = "*")
public class MaterialRequisitionControllerImpl implements MaterialRequisitionController {
	@Autowired
	private MaterialRequisitionServiceImpl materialRequisitionservice;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> getMaterialRequisitions() {
		try {
			List<MaterialRequisitionDto> materialRequisitions =  materialRequisitionservice.getMaterialRequisitions();
			if(materialRequisitions.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialRequisitions);
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
	public ResponseEntity<ResponseDto> updateMaterialRequisition(MaterialRequisitionDto materialRequisitionDto) {
		try {
			String res = materialRequisitionservice.updateMaterialRequisition(materialRequisitionDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialRequisitionDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialRequisition not found");
				responseDto.setContent(materialRequisitionDto);
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
	public ResponseEntity<ResponseDto> getMaterialRequisition(String materialRequisitionid) {
		try {
			MaterialRequisitionDto materialRequisition =  materialRequisitionservice.getMaterialRequisition(materialRequisitionid);
			if(materialRequisition==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialRequisition not found");
				responseDto.setContent(materialRequisition);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialRequisition);
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
	public ResponseEntity<ResponseDto> saveMaterialRequisition(MaterialRequisitionDto materialRequisitionDto) {
		try {
			String res = materialRequisitionservice.saveMaterialRequisition(materialRequisitionDto);
			if(res.equals("00")){
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialRequisitionDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("06")) {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("MaterialRequisition alrady exist");
				responseDto.setContent(materialRequisitionDto);
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
	public ResponseEntity<ResponseDto> activeinactiveMaterialRequisition(ActiveInactiveEntityDto aiedto){
		try {
			String res = materialRequisitionservice.activeinactiveMaterialRequisition(aiedto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(aiedto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialRequisition not exists");
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
