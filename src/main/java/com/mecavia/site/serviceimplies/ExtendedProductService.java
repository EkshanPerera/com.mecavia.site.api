package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ShrinkedProductDto;
import com.mecavia.site.repo.ProductRepoV1;

@Service
@Transactional
public class ExtendedProductService implements com.mecavia.site.service.ExtendedProductService{
	
	@Autowired
	private ProductRepoV1 productRepoV1;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ShrinkedProductDto> getProducts() {
		return productRepoV1.getShrinkedProducts();
	}
	
	
	@Override
	public ShrinkedProductDto getShrinkedProduct(int id) {
		if(productRepoV1.existsById(id)) {
			return productRepoV1.getShrinkedProduct(id);
		}else {
			throw new NullPointerException();
		}
		
	}
	

	@Override
	public ProductDto getProduct(int id) {
		if(productRepoV1.existsById(id)) {
			return modelMapper.map(productRepoV1.findByid(id), ProductDto.class);
		}else {
			throw new NullPointerException();
		}
		
	}
}
