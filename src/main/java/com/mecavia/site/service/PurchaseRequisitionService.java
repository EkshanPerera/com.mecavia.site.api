package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.PurchaseRequisitionDto;

public interface PurchaseRequisitionService {
	String savePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitiondto);
	List<PurchaseRequisitionDto> getPurchaseRequisitions();
	String updatePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitiondto);
	PurchaseRequisitionDto getPurchaseRequisition(String purchaseRequisitionid);
	String activeinactivePurchaseRequisition(ActiveInactiveEntityDto aiedto);
}
