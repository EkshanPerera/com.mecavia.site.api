package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.CustomerOrder;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillOfMaterialDto {
	private int id; 
	private String code;
	private CustomerOrder customerOrder;
	private List<BOMMaterialDto> bomMaterials;
	private double totalcost;
	private Status status;
}
