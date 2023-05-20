package com.mecavia.site.service;

import com.mecavia.site.dto.InventoryDto;

public interface InventoryService {
	String addToInventory(InventoryDto inventoryDto);
	String removeFromInventory(int pcsmid,int status);
	String updateInventory(InventoryDto inventoryDto);
	InventoryDto getInventory(int pcsmid,int status);
}
