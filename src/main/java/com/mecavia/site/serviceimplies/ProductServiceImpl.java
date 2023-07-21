package com.mecavia.site.serviceimplies;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.Stock;
import com.mecavia.site.repo.ProductRepo;
import com.mecavia.site.repo.StockRepo;
import com.mecavia.site.service.ProductService;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private StockRepo stockRepo;

	@Override
	public String saveProduct(ProductDto productDto) {
		if(productRepo.existsById(productDto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			
			Product product = modelMapper.map(productDto, Product.class);
			product = productRepo.saveAndFlush(product);
	        Stock stock = new Stock();
	        stock.setProductid(product);
	        stock.setStatus(product.getStatus());
	        stockRepo.save(stock);
			return VarList.RSP_SUCCESS;
			
		}
	}

	@Override
	public String updateProduct(ProductDto productDto) {
		if(productRepo.existsById(productDto.getId())) {
			productRepo.save(modelMapper.map(productDto, Product.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public String activeInactiveProduct(ActiveInactiveEntityDto activeInactiveEntityDto) {
		if(productRepo.existsById(activeInactiveEntityDto.getId())) {
			int res = productRepo.activeinactiveProduct(activeInactiveEntityDto.getId(), activeInactiveEntityDto.getCode(), activeInactiveEntityDto.getStatus().ordinal());
			res = stockRepo.activeinactiveStock(activeInactiveEntityDto.getId(), activeInactiveEntityDto.getStatus().ordinal()); 
			if (res > 0) {
			    return VarList.RSP_SUCCESS;
			} else {
			    return VarList.RSP_ERROR;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public ProductDto getProduct(int id) {
		Product product = productRepo.findById(id).get();
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getProducts() {
		List<Product> products = productRepo.findAll();
		return modelMapper.map(products, new TypeToken<List<ProductDto>>(){}.getType());
	}

}
