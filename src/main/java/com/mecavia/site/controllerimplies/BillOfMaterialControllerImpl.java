package com.mecavia.site.controllerimplies;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.BillOfMaterialController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.BillOfMaterialDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.BillOfMaterialServiceImpl;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/billofmaterialctrl")
@CrossOrigin(origins = "*")
public class BillOfMaterialControllerImpl implements BillOfMaterialController {
	@Autowired
	private BillOfMaterialServiceImpl billOfMaterialservice;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> getBillOfMaterials() {
		try {
			List<BillOfMaterialDto> BillOfMaterials =  billOfMaterialservice.getBillOfMaterials();
			if(BillOfMaterials.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(BillOfMaterials);
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
	public ResponseEntity<ResponseDto> updateBillOfMaterial(BillOfMaterialDto BillOfMaterialDto) {
		try {
			String res = billOfMaterialservice.updateBillOfMaterial(BillOfMaterialDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(BillOfMaterialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("BillOfMaterial not found");
				responseDto.setContent(BillOfMaterialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("internal server error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> getBillOfMaterial(String BillOfMaterialid) {
		try {
			BillOfMaterialDto BillOfMaterial =  billOfMaterialservice.getBillOfMaterial(BillOfMaterialid);
			if(BillOfMaterial==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("BillOfMaterial not found");
				responseDto.setContent(BillOfMaterial);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(BillOfMaterial);
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
	public ResponseEntity<ResponseDto> saveBillOfMaterial(BillOfMaterialDto BillOfMaterialDto) {
		try {
			String res = billOfMaterialservice.saveBillOfMaterial(BillOfMaterialDto);
			if(res.equals("00")){
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(BillOfMaterialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("06")) {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("BillOfMaterial alrady exist");
				responseDto.setContent(BillOfMaterialDto);
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
	public ResponseEntity<ResponseDto> activeinactiveBillOfMaterial(ActiveInactiveEntityDto aiedto){
		try {
			String res = billOfMaterialservice.activeinactiveBillOfMaterial(aiedto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(aiedto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("BillOfMaterial not exists");
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
