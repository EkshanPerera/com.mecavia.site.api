package com.mecavia.site.serviceimplies;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ProductDto;
import com.mecavia.site.dto.ProductVariantDto;
import com.mecavia.site.entity.Colour;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.Size;
import com.mecavia.site.repo.ColourRepo;
import com.mecavia.site.repo.ProductRepoV1;
import com.mecavia.site.repo.SizeRepo;
import com.mecavia.site.util.VarList;


@Service
@Transactional
public class ProductServiceV1  implements com.mecavia.site.service.ProductServiceV1 {
	@Autowired
	private ProductRepoV1 productRepoV1;
	
	@Autowired
	private SizeRepo sizerepo;
	
	@Autowired
	private ColourRepo colourrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PCSMappingService pcsmservice;
	
	@Autowired
	private ProductDto productDto;
	
	@Override
	public String saveProduct(ProductDto productDto) {
		if(productRepoV1.existsById(productDto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			productRepoV1.save(modelMapper.map(productDto, Product.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateProduct(ProductDto productDto) {
		if(productRepoV1.existsById(productDto.getId())) {
			productRepoV1.save(modelMapper.map(productDto, Product.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public ProductDto savevariant(ProductVariantDto productVariantDto) {
			Product product = productRepoV1.findById(productVariantDto.getProductid()).get();
			modelMapper.map(product,productDto);
			if(sizerepo.existsById(productVariantDto.getSizeid())) {
				Size size = sizerepo.findById(productVariantDto.getSizeid()).get();
				productDto.addvariantDTO(size);
			}
			if(colourrepo.existsById(productVariantDto.getColourid())) {
				Colour colour = colourrepo.findById(productVariantDto.getColourid()).get();
				productDto.addvariantDTO(colour);
			}
			try {
				productRepoV1.save(modelMapper.map(productDto, Product.class));
				pcsmservice.addPCSMapping(productVariantDto.getProductid());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return productDto;
	}

	@Override
	public ProductDto deductvariant(ProductVariantDto productVariantDto) {
		Product product = productRepoV1.findById(productVariantDto.getProductid()).get();
		modelMapper.map(product,productDto);
		if(sizerepo.existsById(productVariantDto.getSizeid())) {
			Size size = sizerepo.findById(productVariantDto.getSizeid()).get();
			productDto.removevariantDTO(size);
		}
		if(colourrepo.existsById(productVariantDto.getColourid())) {
			Colour colour = colourrepo.findById(productVariantDto.getColourid()).get();
			productDto.removevariantDTO(colour);
		}
		try {
			productRepoV1.save(modelMapper.map(productDto, Product.class));
			pcsmservice.removePCSMapping(productVariantDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return productDto;
	}

	@Override
	public String activeInactiveProduct(ActiveInactiveEntityDto activeInactiveEntityDto) {
		if(productRepoV1.existsById(activeInactiveEntityDto.getId())) {
			int res = productRepoV1.activeinactiveProduct(activeInactiveEntityDto.getId(), activeInactiveEntityDto.getCode(), activeInactiveEntityDto.getStatus().ordinal());
			
			if (res > 0) {
			    return VarList.RSP_SUCCESS;
			} else {
			    return VarList.RSP_ERROR;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	
}
