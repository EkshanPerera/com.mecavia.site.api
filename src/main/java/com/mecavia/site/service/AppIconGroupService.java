package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.AppIconGroupDto;

public interface AppIconGroupService {
	String saveAppIconGroup(AppIconGroupDto appIconGroupDto);
	String updateAppIconGroup(AppIconGroupDto appIconGroupDto);
	List<AppIconGroupDto> getAppIconGroups();
	AppIconGroupDto getappIconGroup(int appIconGroupId);
	String activeinactiveAppIconGroup(ActiveInactiveEntityDto aiedto);
}
