package com.eams.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eams.entity.MaintenanceLog;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog,Long> {
	//Finding Maintenance Log based on Asset Id
	List<MaintenanceLog> findByAssetId(Long AssetId);
	//Finding Maintenance Log based on Maintenance Id
	List<MaintenanceLog> findByMaintenanceId(Long MaintenanceId);
	//Finding Maintenance Log based on Scheduled date
	List<MaintenanceLog> findByScheduledDate(LocalDate scheduledDate);
	//Finding Maintenance Log based on Completed date
	List<MaintenanceLog> findByCompletedDate(LocalDate completedDate);
}
