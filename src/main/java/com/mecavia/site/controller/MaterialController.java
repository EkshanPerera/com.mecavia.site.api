package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialDto;
import com.mecavia.site.dto.ResponseDto;

public interface MaterialController {
	@PostMapping("/savematerial")
	ResponseEntity<ResponseDto> saveMaterial (@RequestBody MaterialDto materialDto);
	@PutMapping("/updatematerial")
	ResponseEntity<ResponseDto> updateMaterial(@RequestBody MaterialDto materialDto);
	@PostMapping("/activeinactivematerial")
	ResponseEntity<ResponseDto> activeinactiveMaterial(@RequestBody ActiveInactiveEntityDto activeInactiveEntityDto);
	@GetMapping("/getmaterial/{materialid}")
	ResponseEntity<ResponseDto> getMaterial(@PathVariable String materialid);
	@GetMapping("/getmaterials")
	ResponseEntity<ResponseDto> getMaterials();
}
