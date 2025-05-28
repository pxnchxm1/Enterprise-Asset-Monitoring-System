package com.eams.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.entity.Asset;
import com.eams.entity.UptimeLog;
import com.eams.repository.AssetRepository;
import com.eams.repository.UptimeLogRepository;

@Service
public class UptimeLogService {
	@Autowired 
	private UptimeLogRepository repo;
	@Autowired 
	private AssetRepository assetRepo;
	
	public UptimeLog createlog(Long asset_id, UptimeLog log) {
		Asset asset = assetRepo.findById(asset_id).orElseThrow();
		UptimeLog newlog = new UptimeLog();
		newlog.setAsset(asset);
		newlog.setEndTime(log.getEndTime());
		newlog.setStartTime(LocalDateTime.now());
		newlog.setUptimeLogStatus(log.getUptimeLogStatus());
		return repo.save(newlog);
	}
	public List<UptimeLog> getLogs(Long asset_id){
		Asset asset = assetRepo.findById(asset_id).orElseThrow();
		return repo.findAll().stream().filter(log->log.getAsset().getAsset_id().
				equals(asset.getAsset_id())).collect(Collectors.toList());
	}
	public List<UptimeLog> getAllLogs(){
		return repo.findAll();
	}
	
	
}
