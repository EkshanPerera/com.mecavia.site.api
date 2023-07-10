package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.AppIconGroupDto;

public interface AppIconGroupController {
	@PostMapping("/saveappicongroup")
	ResponseEntity<ResponseDto> saveAppIconGroup(@RequestBody AppIconGroupDto appIconGroupDto);
	@PutMapping("/updateappicongroup")
	ResponseEntity<ResponseDto> updateAppIconGroup(@RequestBody AppIconGroupDto appIconGroupDto);
	@PostMapping("/activeinactiveappicongroup")
	ResponseEntity<ResponseDto> activeInactiveAppIconGroup(@RequestBody ActiveInactiveEntityDto activeInactiveEntityDto);
	@GetMapping("/getappicongroups")
	ResponseEntity<ResponseDto> getAppIconGroups();
	@GetMapping("/getappicongroup/{appicongroupid}")
	ResponseEntity<ResponseDto> getAppIconGroup(@PathVariable String appicongroupid);
}
