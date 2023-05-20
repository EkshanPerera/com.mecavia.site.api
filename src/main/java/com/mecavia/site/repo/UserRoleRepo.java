package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
