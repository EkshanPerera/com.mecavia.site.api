package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVariantDto {
	private int productid;
	private int colourid;
	private int sizeid;
}
