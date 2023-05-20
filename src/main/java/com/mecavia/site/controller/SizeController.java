package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.SizeDto;

public interface SizeController {
	@GetMapping("/getsizes")
	ResponseEntity<ResponseDto> getSizes();
	@PostMapping("/savesize")
	ResponseEntity<ResponseDto> saveSize(@RequestBody SizeDto sizedto);
	@PutMapping("/updatesize")
	ResponseEntity<ResponseDto> updateSize(@RequestBody SizeDto sizedto);
	@GetMapping("/getsize/{sizeid}")
	ResponseEntity<ResponseDto> getSize(@PathVariable String sizeid);
	@PostMapping("/activeinactivesize")
	ResponseEntity<ResponseDto> activeinactiveSize(@RequestBody ActiveInactiveEntityDto aiedto);
}
