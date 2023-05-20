package com.mecavia.site.service;

import com.mecavia.site.dto.InventoryDtoV1;

public interface InventoryServiceV1 {
	String addToInventory(InventoryDtoV1 inventoryDtoV1);
	String removeFromInventory(int pcsmid,int status);
	String updateInventory(InventoryDtoV1 inventoryDtoV1);
	InventoryDtoV1 getInventory(int pcsmid,int status);
}
