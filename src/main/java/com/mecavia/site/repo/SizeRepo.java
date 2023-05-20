package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Size;

public interface SizeRepo extends JpaRepository<Size, Integer> {
	@Modifying
	@Query(value ="update size set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveColour(int id,String code,int status);
	
	Size findByid(int sizeid);
}
