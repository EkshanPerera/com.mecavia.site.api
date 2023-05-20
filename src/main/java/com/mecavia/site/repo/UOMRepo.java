package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.UOM;

public interface UOMRepo extends JpaRepository<UOM, Integer>{
	@Modifying
	@Query(value ="update uom set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveUOM(int id, String string, int status);
}
