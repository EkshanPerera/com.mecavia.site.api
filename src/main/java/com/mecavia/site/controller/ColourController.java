package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ColourDto;
import com.mecavia.site.dto.ResponseDto;

public interface ColourController {
	@GetMapping("/getcolours")
	ResponseEntity<ResponseDto> getColours();
	@PostMapping("/savecolour")
	ResponseEntity<ResponseDto> saveColour(@RequestBody ColourDto colouDto);
	@PutMapping("/updatecolour")
	ResponseEntity<ResponseDto> updateColour(@RequestBody ColourDto colouDto);
	@GetMapping("/getcolour/{colourid}")
	ResponseEntity<ResponseDto> getColour(@PathVariable String colourid);
	@PostMapping("/activeinactivecolour")
	ResponseEntity<ResponseDto> activeinactiveColour(@RequestBody ActiveInactiveEntityDto aiedto);
}
