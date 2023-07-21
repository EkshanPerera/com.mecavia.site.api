package com.mecavia.site.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mecavia.site.entity.ProductPrice;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
	private int id;
	private String code;
	private String name;
	private String desc;
	private String img1URL;
	private String img2URL;
	private String img3URL;
	private String img4URL;
	private List<ProductPrice> pricelist;
	private Status status;
	
//	private List<Colour> productcolours;
//	private List<Size> productsizes;
//	public  void addvariantDTO(Size size) {
//		if(!productsizes.contains(size)) productsizes.add(size);
//	}
//	public  void addvariantDTO(Colour colour) {
//		if(!productcolours.contains(colour))productcolours.add(colour);
//	}
//	public void removevariantDTO(Size size) {
//		if(productsizes.contains(size))productsizes.remove(size);
//	}
//	public void removevariantDTO(Colour colour) {
//		if(productcolours.contains(colour))productcolours.remove(colour);
//	}
}
