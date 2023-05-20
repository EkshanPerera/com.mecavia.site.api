package com.mecavia.site.dto;

import com.mecavia.site.entity.Material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BOMMaterialDto {
	private int id;
	private String code;
	private BillOfMaterialDto billOfMaterialDto;
	private Material material;
	private double materialCost;
	private double quantity;
}
