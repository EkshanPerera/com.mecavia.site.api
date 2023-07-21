package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

}
