package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.FinishedGoodsOutNote;

public interface FinishedGoodsOutNoteRepo extends JpaRepository<FinishedGoodsOutNote, Integer> {
	@Modifying
	@Query(value ="update material_out_note set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveFinishedGoodsOutNote(int id,String code,int status);  
	FinishedGoodsOutNote findByid(int id);
}
