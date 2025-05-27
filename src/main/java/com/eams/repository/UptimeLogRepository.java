package com.eams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eams.entity.UptimeLog;

public interface UptimeLogRepository extends JpaRepository<UptimeLog, Long>{
	List<UptimeLog>findByAssetId(Long asset_id);
}
