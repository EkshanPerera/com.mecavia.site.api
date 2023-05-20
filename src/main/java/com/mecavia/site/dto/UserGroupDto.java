
package com.mecavia.site.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mecavia.site.entity.User;
import com.mecavia.site.entity.UserRole;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserGroupDto {
	private int id;
	private String code;
	private String description;
	private List<User> userslist;
	private List<UserRole> roleslist;
	private Status status;
}
