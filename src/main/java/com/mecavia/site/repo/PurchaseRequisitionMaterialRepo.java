package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.PurchaseRequisitionMaterial;

public interface PurchaseRequisitionMaterialRepo extends JpaRepository<PurchaseRequisitionMaterial, Integer> {
	@Modifying
	@Query(value ="update purchaserequisition_material set tot_arrived_count = ?2 where id = ?1",nativeQuery = true)
	int setTotArrivedCount(int id, double totArrivedCount);
	
	@Query("SELECT new com.mecavia.site.entity.PurchaseRequisitionMaterial(p.id, p.code, p.purchaseRequisition, p.material, p.totArrivedCount, p.unitrate, p.quantity) FROM PurchaseRequisitionMaterial p WHERE p.code = ?1")
	PurchaseRequisitionMaterial findBycode(String code);
	
}
