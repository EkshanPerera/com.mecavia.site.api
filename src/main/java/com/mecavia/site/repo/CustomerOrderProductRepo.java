package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.CustomerOrderProduct;

public interface CustomerOrderProductRepo extends JpaRepository<CustomerOrderProduct, Integer> {
	@Modifying
	@Query(value ="update customerorder_product set tot_finished_count = ?2 where id = ?1",nativeQuery = true)
	int setTotFinishedCount(int id, double totArrivedCount);
	
	@Query("SELECT new com.mecavia.site.entity.CustomerOrderProduct(co.id, co.code, co.customerOrder, co.product, co.quantity,	co.unitrate, co.totFinishedCount) FROM CustomerOrderProduct co WHERE co.code = ?1")
	CustomerOrderProduct findBycode(String code);
	
}
