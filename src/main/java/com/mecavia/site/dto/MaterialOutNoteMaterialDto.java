package com.mecavia.site.dto;

import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.MaterialOutNote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialOutNoteMaterialDto {
	private int id;
	private String code;
    private MaterialOutNote materialOutNote;
    private Material material;
	private double releasedCount;
}
