package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.GoodsReceivedNote;


public interface GoodsReceivedNoteRepo extends JpaRepository<GoodsReceivedNote,Integer> {
	@Modifying
	@Query(value ="update goods_received_note set  status = ?3 where id = ?1 and prcode = ?2",nativeQuery = true)
	int activeinactiveGoodsReceivedNote(int id,String code,int status);  
	GoodsReceivedNote findByid(int id);
}
