package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Stock;
import com.mecavia.site.entity.Product;

public interface StockRepo extends JpaRepository<Stock, Integer> {
	@Modifying
	@Query(value = "update stock set status = ?2 where id = ?1",nativeQuery = true)
	int activeinactiveStock(int id,int status);
		
	@Query("SELECT new com.mecavia.site.entity.Stock(g.id, g.productid, g.itemcount, g.status) FROM Stock g WHERE g.productid = ?1")
	Stock findByProduct(Product product);
}
