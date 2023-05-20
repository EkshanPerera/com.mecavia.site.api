package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.PCSMapping;
import com.mecavia.site.util.Status;

public interface PCSMappingRepo extends JpaRepository<PCSMapping, Integer> {
	@Modifying
	@Query(value = "insert into pcsmapping(colourid,productid,sizeid,status) values(?1,?2,?3,?4)",nativeQuery = true)
	int insertOnly(int colourid, int productid,int sizeid,int status);
	@Modifying
	@Query(value = "update pcsmapping set status = ?3 where colourid = ?1 and productid = ?2",nativeQuery = true)
	int updateColourPCSM(int colourid, int productid,int status);
	@Modifying
	@Query(value = "update pcsmapping set status = ?3 where sizeid = ?2 and productid = ?1",nativeQuery = true)
	int updateSizesPCSM(int productid,int sizeid,int status);
	@Modifying
	@Query(value = "update pcsmapping set status = ?4 where sizeid = ?3 and productid = ?2 and colourid = ?1",nativeQuery = true)
	int updatePCSM(int colourid, int productid,int sizeid,int status);
	
	PCSMapping findPCSMappingByColouridAndSizeidAndProductid(int colourid,int sizeid,int productid);
	
	boolean existsByProductidAndSizeidAndColouridAndStatus(int sizeid,int productid,int colourid,Status status);
}
