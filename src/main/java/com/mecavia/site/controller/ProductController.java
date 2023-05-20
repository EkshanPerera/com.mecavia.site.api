package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ResponseDto;

public interface ProductController {
	@PostMapping("/saveproduct")
	ResponseEntity<ResponseDto> saveProduct (@RequestBody ProductDto productDto);
	@PutMapping("/updateproduct")
	ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductDto productDto);
	@PostMapping("/activeinactiveproduct")
	ResponseEntity<ResponseDto> activeinactiveProduct(@RequestBody ActiveInactiveEntityDto activeInactiveEntityDto);
	@GetMapping("/getproduct/{productid}")
	ResponseEntity<ResponseDto> getProduct(@PathVariable String productid);
	@GetMapping("/getproducts")
	ResponseEntity<ResponseDto> getProducts();
}
