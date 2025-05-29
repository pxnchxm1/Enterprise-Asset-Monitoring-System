package com.eams.service;

import java.util.List;



import com.eams.entity.Alert;

import com.eams.entity.AlertType;

public interface AlertServiceInterface {
			
			public Alert createAlert(Long assetId, AlertType type, String message);
			public List<Alert> getAll();
			public Alert resolveAlert(Long id) ;
}
