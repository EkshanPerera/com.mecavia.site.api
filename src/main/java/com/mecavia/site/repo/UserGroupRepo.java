package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.UserGroup;

public interface UserGroupRepo extends JpaRepository<UserGroup, Integer>{
	@Modifying
	@Query(value ="update user_group set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveUserGroup(int id, String string, int status);

}
