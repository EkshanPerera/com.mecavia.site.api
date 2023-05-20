package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UOMDto;

public interface UOMService {
	String saveUOM(UOMDto uOMDto);
	String updateUOM(UOMDto uOMDto);
	List<UOMDto> getUOMs();
	UOMDto getuOM(int uOMId);
	String activeinactiveUOM(ActiveInactiveEntityDto aiedto);
}
