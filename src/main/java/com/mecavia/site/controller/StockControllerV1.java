package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.StockDtoV1;

public interface StockControllerV1 {
	@PostMapping("/savestock")
	ResponseEntity<ResponseDto> saveStock(@RequestBody StockDtoV1 stock);
	@PutMapping("/closestock")
	ResponseEntity<ResponseDto> closeStock(@RequestBody StockDtoV1 stock);
}
