package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.BillOfMaterial;
import com.mecavia.site.entity.Client;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderDto {
	private int id;
	private String code;
	private  String jobID;
	private  String jobNumber;
	private Client customerid; 
	private double totalAmount;
	private double grossAmount;
	private String remark;
	private List<CustomerOrderProductDto> customerOrderProducts;
	private String printeddate;
	private Status status;
	private BillOfMaterial billOfMaterial;
}