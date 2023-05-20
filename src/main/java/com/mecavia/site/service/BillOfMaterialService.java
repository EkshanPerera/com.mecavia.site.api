package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.BillOfMaterialDto;

public interface BillOfMaterialService {
	String saveBillOfMaterial(BillOfMaterialDto billOfMaterialdto);
	List<BillOfMaterialDto> getBillOfMaterials();
	String updateBillOfMaterial(BillOfMaterialDto billOfMaterialdto);
	BillOfMaterialDto getBillOfMaterial(String billOfMaterialid);
	String activeinactiveBillOfMaterial(ActiveInactiveEntityDto aiedto);
}
