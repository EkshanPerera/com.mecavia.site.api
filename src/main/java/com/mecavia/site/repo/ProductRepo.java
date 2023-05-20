package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	@Modifying
	@Query(value = "update product set status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveProduct(int id,String code,int status);
}
