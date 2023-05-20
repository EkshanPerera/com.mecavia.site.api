package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.UserGroupDto;

public interface UserGroupController {
	@PostMapping("/saveusergroup")
	ResponseEntity<ResponseDto> saveUserGroup(@RequestBody UserGroupDto userGroupDto);
	@PutMapping("/updateusergroup")
	ResponseEntity<ResponseDto> updateUserGroup(@RequestBody UserGroupDto userGroupDto);
	@PostMapping("/activeinactiveusergroup")
	ResponseEntity<ResponseDto> activeInactiveUserGroup(@RequestBody ActiveInactiveEntityDto activeInactiveEntityDto);
	@GetMapping("/getusergroups")
	ResponseEntity<ResponseDto> getUserGroups();
	@GetMapping("/getusergroup/{usergroupid}")
	ResponseEntity<ResponseDto> getUserGroup(@PathVariable String usergroupid);
}
