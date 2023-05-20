package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Email;

public interface EmailRepo extends JpaRepository<Email, Integer> {

}
