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

//Makes the class as Service component
@Service
public class UptimeLogService {
	
	//Dependency Injection - Automatically injects objects of UptimeLogRepository
	@Autowired   
	private UptimeLogRepository repo;
	
	//Dependency Injection - Automatically injects objects of AssetRepository
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
		
		//Gets Asset using it's ID and throws error if not found
		Asset asset = assetRepo.findById(asset_id).orElseThrow();
		return repo.findAll().stream().filter(log->log.getAsset().getAsset_id().
				equals(asset.getAsset_id())).collect(Collectors.toList());
	}
	
	//Getting all the logs
	public List<UptimeLog> getAllLogs(){
		return repo.findAll();
	}
	
	
}
