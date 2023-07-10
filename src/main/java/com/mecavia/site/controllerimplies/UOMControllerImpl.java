package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.UOMController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.UOMDto;
import com.mecavia.site.serviceimplies.UOMServiceImpl;
import com.mecavia.site.util.VarList;
@RestController
@RequestMapping("/api/uomctrl")
@CrossOrigin(origins = "*")
public class UOMControllerImpl implements UOMController {
	@Autowired
	private UOMServiceImpl uOMServiceImpl;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> saveUOM(UOMDto uOMDto) {
		try {
			String res = uOMServiceImpl.saveUOM(uOMDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(uOMDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("user group exists");
				responseDto.setContent(uOMDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("internal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> updateUOM(UOMDto uOMDto) {
		try {
			String res = uOMServiceImpl.updateUOM(uOMDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(uOMDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("user group not exists");
				responseDto.setContent(uOMDto);
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
	public ResponseEntity<ResponseDto> activeInactiveUOM(ActiveInactiveEntityDto activeInactiveEntityDto) {
		try {
			String res = uOMServiceImpl.activeinactiveUOM(activeInactiveEntityDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(activeInactiveEntityDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("user not exists");
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

	@Override
	public ResponseEntity<ResponseDto> getUOMs() {
		try {
			List<UOMDto> res = uOMServiceImpl.getUOMs();
			if(res.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("No data found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("Success");
				responseDto.setContent(res);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("internal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<ResponseDto> getUOM(String uomid) {
		try {
			UOMDto res = uOMServiceImpl.getuOM(Integer.parseInt(uomid));
			if(res==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("No data found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("Success");
				responseDto.setContent(res);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("internal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
