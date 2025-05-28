package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eams.entity.Alert;
import com.eams.entity.AlertStatus;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.repository.AlertRepository;


interface AssetRepository extends JpaRepository<Asset,Long>{}
public class AlertService {
	
	@Autowired
	private AlertRepository alertRepo;
	@Autowired
	private AssetRepository assetRepo;
	
	
	public Alert createAlert(Long assetId, String type, String message) {
		Asset asset = assetRepo.findById(assetId).orElseThrow();
		Alert alert = new Alert();
		alert.setAsset_id(asset.getAsset_id());
		alert.setType(AlertType.valueOf(type));
		alert.setMessage(message);
		alert.setStatus(AlertStatus.ACTIVE);
		alertRepo.save(alert);
		return alert;
	}
	public List<Alert> getAll(){
		return alertRepo.findAll();
	}
	public Alert resolveAlert(Long id) {
		Alert alert = alertRepo.findById(id).orElseThrow();
		alert.setStatus(AlertStatus.RESOLVED);
		return alertRepo.save(alert);
	}
}
