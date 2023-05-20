package com.mecavia.site.dto;

import com.mecavia.site.entity.GoodsReceivedNote;
import com.mecavia.site.entity.PurchaseRequisitionMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsReceivedNoteMaterialDto {
	private int id;
	private String code;
	private String ordercode;
    private GoodsReceivedNote goodsReceivedNote;
    private PurchaseRequisitionMaterial prmaterial;
	private double arrivedCount;
}
