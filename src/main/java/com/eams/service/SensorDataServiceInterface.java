package com.eams.service;


import java.util.Optional;

import com.eams.entity.SensorData;


public interface SensorDataServiceInterface {
	
    
    // get asset_id and store in asset
    public Optional<SensorData> getSensorDatabyAssetID(Long asset_id) ;
    public Boolean sendSensorData(SensorData data);
        
}
