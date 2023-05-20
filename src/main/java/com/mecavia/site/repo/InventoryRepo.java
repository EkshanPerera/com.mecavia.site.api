package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Inventory;
import com.mecavia.site.util.InventoryId;

public interface InventoryRepo extends JpaRepository<Inventory, InventoryId>{
	@Modifying
	@Query(value = "update inventory set status = ?1 where stockid =?3 and status=?2",nativeQuery = true)
	int closeInventory(int chsts,int currsts,int stockid);
	
	@Query(value = "select exists(select * from inventory where pcsmid = ?1 and status = ?2)",nativeQuery = true)
	int existsBypcsmidandsatus(int pcsmid,int status);
		
	@Query("select new com.mecavia.site.entity.Inventory(inventoryId,itemcount,price,pcsmid) from Inventory where pcsmid = ?1 and status = ?2")
	Inventory findBypcsmidandstatus(int pcsmid,int status);
	
	@Modifying
	@Query(value = "delete from inventory where pcsmid = ?1 and status = ?2",nativeQuery = true)
	int deleteBypcsmidandstatus(int pcsmid,int status);
}
