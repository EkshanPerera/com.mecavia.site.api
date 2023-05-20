package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialRequisitionDto;

public interface MaterialRequisitionService {
	String saveMaterialRequisition(MaterialRequisitionDto goodsReceivedNotedto);
	List<MaterialRequisitionDto> getMaterialRequisitions();
	String updateMaterialRequisition(MaterialRequisitionDto goodsReceivedNotedto);
	MaterialRequisitionDto getMaterialRequisition(String goodsReceivedNoteid);
	String activeinactiveMaterialRequisition(ActiveInactiveEntityDto aiedto);
}
