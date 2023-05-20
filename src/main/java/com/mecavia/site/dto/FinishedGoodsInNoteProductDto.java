package com.mecavia.site.dto;

import com.mecavia.site.entity.CustomerOrderProduct;

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
    private FinishedGoodsInNoteDto finishedGoodsInNoteDto;
    private CustomerOrderProduct coproduct;
	private double finishedCount;
}
