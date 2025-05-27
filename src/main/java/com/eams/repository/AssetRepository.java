package com.eams.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eams.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
	
	

}
