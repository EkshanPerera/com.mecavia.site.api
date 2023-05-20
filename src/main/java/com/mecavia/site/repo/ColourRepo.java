package com.mecavia.site.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Colour;

public interface ColourRepo extends JpaRepository<Colour, Integer> {
	@Modifying
	@Query(value ="update colour set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveColour(int id,String code,int status);
	
	Colour findByid(int colourid); 
}
