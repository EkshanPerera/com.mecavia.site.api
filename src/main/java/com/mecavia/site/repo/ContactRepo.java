package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

}
