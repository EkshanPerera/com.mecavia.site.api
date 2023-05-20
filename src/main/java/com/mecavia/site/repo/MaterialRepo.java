package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Material;

public interface MaterialRepo extends JpaRepository<Material, Integer>{
	@Modifying
	@Query(value = "update material set status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveMaterial(int id,String code,int status);
}
