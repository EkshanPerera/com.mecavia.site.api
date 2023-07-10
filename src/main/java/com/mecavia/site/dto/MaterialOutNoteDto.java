package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.MaterialRequisition;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialOutNoteDto {
	private int id;
	private String code;
	private String enterddate;
	private MaterialRequisition materialRequisition;
	private List<MaterialOutNoteMaterialDto> materialOutNoteMaterials;
	private String printeddate;
	private String remark;
	private Status status;
}
