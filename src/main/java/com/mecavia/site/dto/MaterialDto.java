package com.mecavia.site.dto;
import com.mecavia.site.entity.UOM;
import com.mecavia.site.util.MaterialType;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialDto {
	private int id;
	private String code;
	private String description;
	private MaterialType materialType;
	private UOM uomid;
	private double price;
	private Status status;
}
