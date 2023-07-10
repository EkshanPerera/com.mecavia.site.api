package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.AppIconGroup;

public interface AppIconGroupRepo extends JpaRepository<AppIconGroup, Integer>{
	@Modifying
	@Query(value ="update app_icon_group set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveAppIconGroup(int id, String string, int status);

}
