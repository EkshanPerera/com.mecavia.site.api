package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.UOMDto;

public interface UOMController {
	@PostMapping("/saveuom")
	ResponseEntity<ResponseDto> saveUOM(@RequestBody UOMDto uOMDto);
	@PutMapping("/updateuom")
	ResponseEntity<ResponseDto> updateUOM(@RequestBody UOMDto uOMDto);
	@PostMapping("/activeinactiveuom")
	ResponseEntity<ResponseDto> activeInactiveUOM(@RequestBody ActiveInactiveEntityDto activeInactiveEntityDto);
	@GetMapping("/getuoms")
	ResponseEntity<ResponseDto> getUOMs();
	@GetMapping("/getuom/{uomid}")
	ResponseEntity<ResponseDto> getUOM(@PathVariable String uomid);
}
