package com.mecavia.site.controllerimplies;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.MaterialOutNoteController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialOutNoteDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.MaterialOutNoteServiceImpl;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/materialoutnotectrl")
@CrossOrigin(origins = "*")
public class MaterialOutNoteControllerImpl implements MaterialOutNoteController {
	@Autowired
	private MaterialOutNoteServiceImpl materialOutNoteservice;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> getMaterialOutNotes() {
		try {
			List<MaterialOutNoteDto> materialOutNotes =  materialOutNoteservice.getMaterialOutNotes();
			if(materialOutNotes.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialOutNotes);
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
	public ResponseEntity<ResponseDto> updateMaterialOutNote(MaterialOutNoteDto materialOutNoteDto) {
		try {
			String res = materialOutNoteservice.updateMaterialOutNote(materialOutNoteDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialOutNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialOutNote not found");
				responseDto.setContent(materialOutNoteDto);
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
	public ResponseEntity<ResponseDto> getMaterialOutNote(String materialOutNoteid) {
		try {
			MaterialOutNoteDto materialOutNote =  materialOutNoteservice.getMaterialOutNote(materialOutNoteid);
			if(materialOutNote==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialOutNote not found");
				responseDto.setContent(materialOutNote);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialOutNote);
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
	public ResponseEntity<ResponseDto> saveMaterialOutNote(MaterialOutNoteDto materialOutNoteDto) {
		try {
			String res = materialOutNoteservice.saveMaterialOutNote(materialOutNoteDto);
			if(res.equals("00")){
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialOutNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("06")) {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("MaterialOutNote alrady exist");
				responseDto.setContent(materialOutNoteDto);
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
	public ResponseEntity<ResponseDto> activeinactiveMaterialOutNote(ActiveInactiveEntityDto aiedto){
		try {
			String res = materialOutNoteservice.activeinactiveMaterialOutNote(aiedto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(aiedto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("MaterialOutNote not exists");
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
