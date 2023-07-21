package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.BillOfMaterial;
import com.mecavia.site.entity.User;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialRequisitionDto {
	private int id;
	private String code;
	private String enterddate;
	private BillOfMaterial billOfMaterial;
	private List<MaterialRequisitionMaterialDto> materialRequisitionMaterials;
	private String printeddate;
	private Status status;
	private User enteredUser;
}
