package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.entity.Material;

public interface GeneralStoreRepo extends JpaRepository<GeneralStore, Integer> {
	@Modifying
	@Query(value = "update general_store set status = ?2 where id = ?1",nativeQuery = true)
	int activeinactiveGenaralStore(int id,int status);
		
	@Query("SELECT new com.mecavia.site.entity.GeneralStore(g.id, g.materialid, g.itemcount, g.status) FROM GeneralStore g WHERE g.materialid = ?1")
	GeneralStore findByMaterial(Material material);
}
