package com.mecavia.site.dto;

import com.mecavia.site.entity.CustomerOrder;
import com.mecavia.site.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderProductDto {
	private int id; 
	private String code;
    private CustomerOrder customerOrder;
    private Product product;
	private double quantity;
	private double unitrate;
}
