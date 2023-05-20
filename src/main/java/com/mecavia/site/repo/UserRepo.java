package com.mecavia.site.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.User;


public interface UserRepo extends JpaRepository<User,Integer> {
	@Modifying
	@Query(value ="update user set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveColour(int id,String code,int status);  
	User findByid(int id);
	User findByemail(String email);
	Optional<User> findByEmail(String email);
}
