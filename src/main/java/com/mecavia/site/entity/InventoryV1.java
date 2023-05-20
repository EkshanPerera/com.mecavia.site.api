package com.mecavia.site.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.mecavia.site.util.InventoryId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class InventoryV1 {
	@EmbeddedId
	private InventoryId inventoryId;
	private int itemcount;
	private double price;
	private int pcsmid;
}
