package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.BillOfMaterial;


public interface BillOfMaterialRepo extends JpaRepository<BillOfMaterial,Integer> {
	@Modifying
	@Query(value ="update bill_of_material set  status = ?3 where id = ?1 and prcode = ?2",nativeQuery = true)
	int activeinactiveBillOfMaterial(int id,String code,int status);  
	BillOfMaterial findByid(int id);
}
