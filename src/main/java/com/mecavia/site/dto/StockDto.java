package com.mecavia.site.dto;

import com.mecavia.site.entity.Product;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockDto {
	private int id;
	private Product productid;
	private double itemcount;
	private double releasedItemcount;
	private Status status;
}
