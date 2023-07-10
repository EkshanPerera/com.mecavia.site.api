package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.MaterialOutNote;

public interface MaterialOutNoteRepo extends JpaRepository<MaterialOutNote, Integer> {
	@Modifying
	@Query(value ="update material_out_note set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveMaterialOutNote(int id,String code,int status);  
	MaterialOutNote findByid(int id);
}
