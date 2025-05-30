package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.entity.Alert;
import com.eams.entity.AlertStatus;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.exception.AssetNotFoundException;
import com.eams.repository.AlertRepository;
import com.eams.repository.AssetRepository;

@Service
public class AlertService implements AlertServiceInterface{
	
	@Autowired
	private AlertRepository alertRepo;
	@Autowired
	private AssetRepository assetRepo;
	
	
	public Alert createAlert(Long assetId, AlertType type, String message) {
		Asset asset = assetRepo.findById(assetId).orElseThrow();
		Alert alert = new Alert();
		alert.setAsset_id(asset.getAsset_id());
		alert.setType(type);
		alert.setMessage(message);
		alert.setStatus(AlertStatus.ACTIVE);
		alertRepo.save(alert);
		return alert;
	}
	public List<Alert> getAll(){
		return alertRepo.findAll();
	}
	//method to resolve alert
	public Alert resolveAlert(Long id) {
		Alert alert = alertRepo.findById(id).orElseThrow(()->new AssetNotFoundException("asset is not found"));
		alert.setStatus(AlertStatus.RESOLVED);
		return alertRepo.save(alert);
	}
	
}
