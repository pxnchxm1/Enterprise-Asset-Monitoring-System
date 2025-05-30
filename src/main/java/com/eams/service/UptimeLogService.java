package com.eams.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eams.entity.Alert;
import com.eams.entity.AlertStatus;
import com.eams.entity.Asset;
import com.eams.entity.UpTimeLogStatus;
import com.eams.entity.UptimeLog;
import com.eams.exception.AssetNotFoundException;
import com.eams.repository.AlertRepository;
import com.eams.repository.AssetRepository;
import com.eams.repository.UptimeLogRepository;

//Makes the class as Service component
@Service
public class UptimeLogService implements UptimeLogServiceInterface {
	
	//Dependency Injection - Automatically injects objects of UptimeLogRepository
	@Autowired   
	private UptimeLogRepository repo;
	
	@Autowired
	private AlertRepository alertRepo;
	
	//Dependency Injection - Automatically injects objects of AssetRepository
	@Autowired 
	private AssetRepository assetRepo;
	
	
	public void methodToScheduleUptimeLogs() {
		
	}
	@Scheduled(fixedRate=10000)
	public void createlog() {
			List<Alert> alerts = alertRepo.findAll();
			for(Alert al : alerts) {
				Long asset_id = al.getAsset_id();
				Asset asset = assetRepo.findById(asset_id).orElseThrow(
						()->  new AssetNotFoundException("Asset not found !! "));
				UptimeLog newlog = new UptimeLog();
				newlog.setAsset(asset);
				newlog.setStartTime(LocalDateTime.now());
				newlog.setEndTime(LocalDateTime.now().plusSeconds(10));
				
				if(al.getStatus()==AlertStatus.ACTIVE) {
					newlog.setUptimeLogStatus(UpTimeLogStatus.DOWN);
				}
				if(al.getStatus()==AlertStatus.RESOLVED)
					newlog.setUptimeLogStatus(UpTimeLogStatus.UP);
				repo.save(newlog);		
			}
		
	}
	public List<UptimeLog> getLogs(Long asset_id){
		
		//Gets Asset using it's ID and throws error if not found
		Asset asset = assetRepo.findById(asset_id).orElseThrow(
				() -> new AssetNotFoundException("Asset with ID " + asset_id + " not found")
				);
		return repo.findAll().stream().filter(log->log.getAsset().getAsset_id().
				equals(asset.getAsset_id())).collect(Collectors.toList());
	}
	
	//Getting all the logs
	public List<UptimeLog> getAllLogs(){
		return repo.findAll();
	}
	
	
}
