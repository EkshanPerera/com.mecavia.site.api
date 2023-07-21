package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mecavia.site.entity.CustomerOrder;


public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Integer> {
	@Modifying
	@Query(value ="update customer_order set  status = ?3 where id = ?1 and code = ?2",nativeQuery = true)
	int activeinactiveCustomerOrder(int id,String code,int status);  
	CustomerOrder findByid(int id);
	
	@Modifying
	@Query(value ="update customer_order set  jobid = ?3 , printeddate = ?4, status = ?5,accepted_date = ?6,accepted_user_id = ?7 where id = ?1 and code = ?2",nativeQuery = true)
	int setOrder(int id,String code,String jobid,String printeddate,int status,String acceptedDate, int acceptedUserId);

}
