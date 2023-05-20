package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UserGroupDto;

public interface UserGroupService {
	String saveUserGroup(UserGroupDto userGroupDto);
	String updateUserGroup(UserGroupDto userGroupDto);
	List<UserGroupDto> getUserGroups();
	UserGroupDto getuserGroup(int userGroupId);
	String activeinactiveUserGroup(ActiveInactiveEntityDto aiedto);
}
