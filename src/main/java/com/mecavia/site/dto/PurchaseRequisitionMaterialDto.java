package com.mecavia.site.dto;

import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.PurchaseRequisition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequisitionMaterialDto {
	private int id; 
	private String code;
    private PurchaseRequisition purchaseRequisition;
    private Material material;
    private double totArrivedCount;
	private double unitrate;
	private double quantity;
}
