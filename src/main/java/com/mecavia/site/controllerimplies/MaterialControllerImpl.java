package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.MaterialController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.MaterialServiceImpl;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping("/api/materialctrl")
@CrossOrigin(origins = "*")
public class MaterialControllerImpl implements MaterialController {

	@Autowired
	private MaterialServiceImpl materialServiceImpl;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> saveMaterial(MaterialDto materialDto) {
		try {
			String res =  materialServiceImpl.saveMaterial(materialDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(materialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Internal server error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> updateMaterial(MaterialDto materialDto) {
		try {
			String res =  materialServiceImpl.updateMaterial(materialDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(materialDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Internal server error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> activeinactiveMaterial(ActiveInactiveEntityDto activeInactiveEntityDto) {
		try {
			String res =  materialServiceImpl.activeInactiveMaterial(activeInactiveEntityDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(activeInactiveEntityDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("fail");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Internal server error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> getMaterial(String materialid) {
		try {
			MaterialDto material = materialServiceImpl.getMaterial(Integer.parseInt(materialid));
			if(material!=null) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(material);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Intrnal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> getMaterials() {
		
		try {
			List<MaterialDto> materialList = materialServiceImpl.getMaterials();
			if(!materialList.isEmpty()) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(materialList);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Intrnal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
