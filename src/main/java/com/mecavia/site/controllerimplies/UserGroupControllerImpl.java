package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.UserGroupController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.UserGroupDto;
import com.mecavia.site.serviceimplies.UserGroupServiceImpl;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping("/api/usergroupctrl")
@CrossOrigin(origins = "*")
public class UserGroupControllerImpl implements UserGroupController {
	@Autowired
	private UserGroupServiceImpl userGroupServiceImpl;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> saveUserGroup(UserGroupDto userGroupDto) {
		try {
			String res = userGroupServiceImpl.saveUserGroup(userGroupDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(userGroupDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("user group exists");
				responseDto.setContent(userGroupDto);
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
	public ResponseEntity<ResponseDto> updateUserGroup(UserGroupDto userGroupDto) {
		try {
			String res = userGroupServiceImpl.updateUserGroup(userGroupDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(userGroupDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("user group not exists");
				responseDto.setContent(userGroupDto);
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
	public ResponseEntity<ResponseDto> activeInactiveUserGroup(ActiveInactiveEntityDto activeInactiveEntityDto) {
		try {
			String res = userGroupServiceImpl.activeinactiveUserGroup(activeInactiveEntityDto);
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
	public ResponseEntity<ResponseDto> getUserGroups() {
		try {
			List<UserGroupDto> res = userGroupServiceImpl.getUserGroups();
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
	public ResponseEntity<ResponseDto> getUserGroup(String usergroupid) {
		try {
			UserGroupDto res = userGroupServiceImpl.getuserGroup(Integer.parseInt(usergroupid));
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
