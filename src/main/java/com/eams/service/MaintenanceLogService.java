package com.eams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.entity.Asset;
import com.eams.entity.MaintenanceLog;
import com.eams.exception.AssetNotFoundException;
import com.eams.repository.MaintenanceLogRepository;

import lombok.extern.slf4j.Slf4j;

import com.eams.repository.AssetRepository;

@Service
@Slf4j
public class MaintenanceLogService implements MaintenanceLogServiceInterface {
    @Autowired 
    private MaintenanceLogRepository repo;
    @Autowired 
    private AssetRepository assetRepo;

    //Scheduling a Task
    public MaintenanceLog schedule(Long assetId, MaintenanceLog mlog){
        Asset asset = assetRepo.findById(assetId)
        		.orElseThrow(()-> new AssetNotFoundException("Asset with ID " + assetId + " not found"));
        MaintenanceLog l =new MaintenanceLog();
        l.setAsset_id(asset.getAsset_id());
        l.setCompletedDate(mlog.getCompletedDate());
        l.setScheduledDate(mlog.getScheduledDate());
        l.setRemarks(mlog.getRemarks());
        log.info("Task is Scheduled sucessfully");
        return repo.save(l);
        
       
    }
     
    //This method can access asset by Id
    public List<MaintenanceLog> getByAssetId(Long assetId) {
    	
    	Asset asset = assetRepo.findById(assetId)
        		.orElseThrow(()-> new AssetNotFoundException("Asset with ID " + assetId + " not found"));
        List<MaintenanceLog> requiredLogs = repo.findAll().stream().filter(x->x.getAsset_id().equals(asset.getAsset_id())).collect(Collectors.toList());
        log.info("Assest found successfully");
        return requiredLogs;
    }
    
    //this method can access all assets
    public List<MaintenanceLog> getAllAssetId() {
    	List<MaintenanceLog> allLog = repo.findAll();
    	log.info("Found all assets successfully");
    	return allLog;
    }
}
