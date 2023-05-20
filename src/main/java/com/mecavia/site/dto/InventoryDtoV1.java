package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import com.mecavia.site.util.InventoryId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryDtoV1 {
	private InventoryId inventoryId;
	private int itemcount;
	private double price;
	private int pcsmid;
}
