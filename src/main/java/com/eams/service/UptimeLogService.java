package com.eams.service;

import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.entity.Asset;
import com.eams.entity.UptimeLog;
import com.eams.repository.AssetRepository;
import com.eams.repository.UptimeLogRepository;

@Service
public class UptimeLogService {
	@Autowired private UptimeLogRepository repo;
	@Autowired private AssetRepository assetRepo;
	public UptimeLog logStatus(Long asset_id, UptimeLog log) {
		Asset asset = assetRepo.findById(asset_id).orElseThrow();
		log.setAsset(asset);
		return repo.save(log);
	}
	public List<UptimeLog>getLogs(Long asset_id){
		return repo.findByAssetId(asset_id);
	}
	
	
}
