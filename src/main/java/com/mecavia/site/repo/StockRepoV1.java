package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.StockV1;
import com.mecavia.site.util.Status;

public interface StockRepoV1 extends JpaRepository<StockV1, Integer> {
	boolean existsByPcsmidAndStatus(int pcsmid,Status status);
	StockV1 findStockByPcsmidAndStatus(int pcsmid,Status status);
	boolean existsByIdAndStatus(int id,Status status);
}
