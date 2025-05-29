package com.eams.service;

import java.util.List;

import com.eams.entity.MaintenanceLog;

public interface MaintenanceLogServiceInterface {
	public MaintenanceLog schedule(Long assetId, MaintenanceLog log);

    public List<MaintenanceLog> getByAssetId(Long assetId);
    public List<MaintenanceLog> getAllAssetId();

}
