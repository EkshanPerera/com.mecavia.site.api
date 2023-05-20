package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.PurchaseRequisition;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsReceivedNoteDto {
	private int id;
	private String invoicenumber;
	private String invocedate;
	private String code;
	private String mradate;
	private String mrano;
	private String enterddate;
	private String remark;
	private Status status;
	private PurchaseRequisition purchaseRequisition;
	private List<GoodsReceivedNoteMaterialDto> goodsReceivedNoteMaterials;
	private String printeddate;
}
