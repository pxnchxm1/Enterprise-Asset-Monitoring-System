package com.eams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eams.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset,Long> {
	
	List<Asset> findByAssignedToId(Long userId);

}
