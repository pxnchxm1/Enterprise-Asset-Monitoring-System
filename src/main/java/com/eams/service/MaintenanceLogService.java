package com.eams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.entity.Asset;
import com.eams.entity.MaintenanceLog;
import com.eams.repository.MaintenanceLogRepository;
import com.eams.repository.AssetRepository;

@Service
public class MaintenanceLogService {
    @Autowired 
    private MaintenanceLogRepository repo;
    @Autowired 
    private AssetRepository assetRepo;

    public MaintenanceLog schedule(Long assetId, MaintenanceLog log){
        Asset asset = assetRepo.findById(assetId).orElseThrow();
        MaintenanceLog l =new MaintenanceLog();
        l.setAsset_id(asset.getAsset_id());
        l.setCompletedDate(log.getCompletedDate());
        return repo.save(l);
    }

    public MaintenanceLog getByAssetId(Long assetId) {
    	Asset asset = assetRepo.findById(assetId).orElseThrow();
        MaintenanceLog requiredLog = repo.findAll().stream().filter(x->x.getAsset_id().equals(asset.getAsset_id())).findFirst().orElseThrow();
        return requiredLog;
    }
    
    public List<MaintenanceLog> getAllAssetId() {
    	List<MaintenanceLog> allLog = repo.findAll();
    	return allLog;
    }
}
