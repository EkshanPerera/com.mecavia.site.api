package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.PurchaseRequisition;


public interface PurchaseRequisitionRepo extends JpaRepository<PurchaseRequisition,Integer> {
	@Modifying
	@Query(value ="update purchase_requisition set  status = ?3 where id = ?1 and prcode = ?2",nativeQuery = true)
	int activeinactivePurchaseRequisition(int id,String code,int status);  
	PurchaseRequisition findByid(int id);
}
