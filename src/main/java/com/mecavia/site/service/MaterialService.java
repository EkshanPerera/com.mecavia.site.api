package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialDto;

public interface MaterialService {
	String saveMaterial (MaterialDto materialDto);
	String updateMaterial(MaterialDto materialDto);
	String activeInactiveMaterial(ActiveInactiveEntityDto activeInactiveEntityDto);
	MaterialDto getMaterial(int id);
	List<MaterialDto> getMaterials();
}
