package com.mecavia.site.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.dto.ShrinkedProductDto;
import com.mecavia.site.entity.Product;

public interface ProductRepoV1 extends JpaRepository<Product, Integer> {
	Product findByid(int id);
	@Query("select new com.mecavia.site.dto.ShrinkedProductDto(id,code,name,img1URL,status) from Product")
	List<ShrinkedProductDto> getShrinkedProducts();
	@Query("select new com.mecavia.site.dto.ShrinkedProductDto(id,code,name,img1URL,status) from Product where id=?1")
	ShrinkedProductDto getShrinkedProduct(int id);
	@Modifying
	@Query(value = "update product set status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveProduct(int id, String code, int ordinal);
}
