package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ProductDto;

public interface ProductService {
	String saveProduct (ProductDto productDto);
	String updateProduct(ProductDto productDto);
	String activeInactiveProduct(ActiveInactiveEntityDto activeInactiveEntityDto);
	ProductDto getProduct(int id);
	List<ProductDto> getProducts();
}
