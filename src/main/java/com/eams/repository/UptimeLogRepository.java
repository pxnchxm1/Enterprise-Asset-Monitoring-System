package com.eams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eams.entity.UptimeLog;

public interface UptimeLogRepository extends JpaRepository<UptimeLog, Long>{

}
