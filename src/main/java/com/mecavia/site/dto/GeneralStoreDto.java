package com.mecavia.site.dto;

import com.mecavia.site.entity.Material;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneralStoreDto {
	private int id;
	private Material materialid;
	private double itemcount;
	private double requestedItemcount;
	private double releasedItemcount;
	private Status status;
}
