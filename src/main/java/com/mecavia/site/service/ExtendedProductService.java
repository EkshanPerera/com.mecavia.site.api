package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ShrinkedProductDto;

public interface ExtendedProductService {
	List<ShrinkedProductDto> getProducts();
	ShrinkedProductDto getShrinkedProduct(int id);
	ProductDto getProduct(int id);
}
