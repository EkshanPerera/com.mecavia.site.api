package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.MaterialRequisition;


public interface MaterialRequisitionRepo extends JpaRepository<MaterialRequisition,Integer> {
	@Modifying
	@Query(value ="update material_requisition set  status = ?3 where id = ?1 and prcode = ?2",nativeQuery = true)
	int activeinactiveMaterialRequisition(int id,String code,int status);  
	MaterialRequisition findByid(int id);
}
