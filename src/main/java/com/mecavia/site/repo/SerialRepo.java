package com.mecavia.site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mecavia.site.entity.Serial;

public interface SerialRepo extends JpaRepository<Serial, Integer> {
	Serial findSerialByServicecode(String code);
}
