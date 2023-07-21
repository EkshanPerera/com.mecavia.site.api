package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.CustomerOrderProduct;
import com.mecavia.site.entity.FinishedGoodsInNote;
import com.mecavia.site.entity.InventoryItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishedGoodsInNoteProductDto {
	private int id;
	private String code;
	private String cordercode;
    private FinishedGoodsInNote finishedGoodsInNote;
    private CustomerOrderProduct coproduct;
	private double finishedCount;
	private List<InventoryItem> inventoryItems;
}
