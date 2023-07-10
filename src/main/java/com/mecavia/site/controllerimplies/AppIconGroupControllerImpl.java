package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.AppIconGroupController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.AppIconGroupDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.AppIconGroupServiceImpl;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping("/api/appicongroupctrl")
@CrossOrigin(origins = "*")
public class AppIconGroupControllerImpl implements AppIconGroupController {
	@Autowired
	private AppIconGroupServiceImpl appIconGroupServiceImpl;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> saveAppIconGroup(AppIconGroupDto appIconGroupGroupDto) {
		try {
			String res = appIconGroupServiceImpl.saveAppIconGroup(appIconGroupGroupDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(appIconGroupGroupDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("user group exists");
				responseDto.setContent(appIconGroupGroupDto);
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
	public ResponseEntity<ResponseDto> updateAppIconGroup(AppIconGroupDto appIconGroupGroupDto) {
		try {
			String res = appIconGroupServiceImpl.updateAppIconGroup(appIconGroupGroupDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(appIconGroupGroupDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("user group not exists");
				responseDto.setContent(appIconGroupGroupDto);
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
	public ResponseEntity<ResponseDto> activeInactiveAppIconGroup(ActiveInactiveEntityDto activeInactiveEntityDto) {
		try {
			String res = appIconGroupServiceImpl.activeinactiveAppIconGroup(activeInactiveEntityDto);
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
	public ResponseEntity<ResponseDto> getAppIconGroups() {
		try {
			List<AppIconGroupDto> res = appIconGroupServiceImpl.getAppIconGroups();
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
	public ResponseEntity<ResponseDto> getAppIconGroup(String appicongroupid) {
		try {
			AppIconGroupDto res = appIconGroupServiceImpl.getappIconGroup(Integer.parseInt(appicongroupid));
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
