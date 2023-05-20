package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.InventoryItem;

public interface InventoryItemRepo extends JpaRepository<InventoryItem, Integer> {

}
