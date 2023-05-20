package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UserDto;

public interface UserService {
	String saveUser(UserDto userdto);
	List<UserDto> getUsers();
	String updateUser(UserDto userdto);
	UserDto getUser(String userid);
	String activeinactiveUser(ActiveInactiveEntityDto aiedto);
}
