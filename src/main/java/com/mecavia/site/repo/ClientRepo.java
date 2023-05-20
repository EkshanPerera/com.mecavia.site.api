package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.Client;


public interface ClientRepo extends JpaRepository<Client,Integer> {
	@Modifying
	@Query(value ="update client set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveClient(int id,String code,int status);  
	Client findByid(int id);
}
