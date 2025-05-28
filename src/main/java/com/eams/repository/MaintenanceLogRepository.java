package com.eams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eams.entity.MaintenanceLog;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog,Long> {
	
}
