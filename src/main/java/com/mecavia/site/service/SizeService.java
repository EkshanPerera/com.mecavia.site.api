package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.SizeDto;

public interface SizeService {
	String saveSize(SizeDto size);
	String updateSize(SizeDto size);
	List<SizeDto> getSizes();
	SizeDto getSize(String sizeid);
	String activeinactiveSize(ActiveInactiveEntityDto aiedto);
}
