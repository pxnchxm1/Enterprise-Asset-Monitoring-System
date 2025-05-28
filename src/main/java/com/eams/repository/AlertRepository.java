package com.eams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eams.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	
}
