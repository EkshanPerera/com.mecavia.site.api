package com.mecavia.site.service;

import com.mecavia.site.dto.PCSMappingDto;
import com.mecavia.site.dto.ProductVariantDto;

public interface PCSMappingService {
	void addPCSMapping(int productid);
	void removePCSMapping(ProductVariantDto pvdto);
	PCSMappingDto getPCSM(PCSMappingDto pcsmDto);
	PCSMappingDto getPCSMById(int id);
}
