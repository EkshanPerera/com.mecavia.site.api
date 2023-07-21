package com.mecavia.site.dto;

import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.FinishedGoodsOutNote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishedGoodsOutNoteProductDto {
	private int id;
	private String code;
    private FinishedGoodsOutNote finishedGoodsOutNote;
    private Product product;
	private double releasedCount;
}
