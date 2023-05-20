package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.SMS;

public interface SMSRepo extends JpaRepository<SMS, Integer>{
}
