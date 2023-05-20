package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.FinishedGoodsInNote;


public interface FinishedGoodsInNoteRepo extends JpaRepository<FinishedGoodsInNote,Integer> {
	@Modifying
	@Query(value ="update finished_goods_in_note set  status = ?3 where id = ?1 and prcode = ?2",nativeQuery = true)
	int activeinactiveFinishedGoodsInNote(int id,String code,int status);  
	FinishedGoodsInNote findByid(int id);
}
