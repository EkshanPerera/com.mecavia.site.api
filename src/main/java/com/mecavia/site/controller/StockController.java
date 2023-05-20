package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.StockDto;

public interface StockController {
	@PostMapping("/savestock")
	ResponseEntity<ResponseDto> saveStock(@RequestBody StockDto stock);
	@PutMapping("/closestock")
	ResponseEntity<ResponseDto> closeStock(@RequestBody StockDto stock);
}
