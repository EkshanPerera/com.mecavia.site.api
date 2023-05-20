package com.mecavia.site.dto;

import com.mecavia.site.entity.BOMMaterial;
import com.mecavia.site.entity.MaterialRequisition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialRequisitionMaterialDto {
	private int id;
	private String code;
	private String ordercode;
    private MaterialRequisition materialRequisition;
    private BOMMaterial bommaterial;
	private double arrivedCount;
}
