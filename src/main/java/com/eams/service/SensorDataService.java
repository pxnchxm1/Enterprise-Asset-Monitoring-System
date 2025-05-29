package com.eams.service;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.entity.SensorData;
import com.eams.exception.InvalidSensorDataException;
import com.eams.repository.AssetRepository;
import com.eams.repository.SensorDataRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorDataService {
    @Autowired
    private SensorDataRepository sdr;
    @Autowired
    private AssetRepository apr;
    
    @Autowired
    private AlertService alertService;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sdr = sensorDataRepository;
    }
    public List<SensorData> getAllSensorData(){
    	return sdr.findAll();
    }
    // get asset_id and store in asset
    public List<SensorData> getSensorDatabyAssetID(Long asset_id) {
    	Asset asset = apr.findById(asset_id)
    		     .orElseThrow(() -> new NoSuchElementException("Asset not found with ID: " + asset_id));

    	List<SensorData> sensorData = sdr.findAll().stream()
    		    .filter(data -> data.getAsset_id().equals(asset.getAsset_id())).collect(Collectors.toList());

        return sensorData;
        
    }
	public Boolean sendSensorData(SensorData data){
            
		 if(data.getPressure()<0) {
         	
         	throw new InvalidSensorDataException("Invalid Pressure Value !");
         }else if(data.getTemperature()<0) {
         	
         	throw new InvalidSensorDataException("Invalid Temperature Value !");
         	
         }else {
        	 SensorData sd = new SensorData();
             sd.setAsset_id(data.getAsset_id());
             sd.setPressure(data.getPressure());
             sd.setTemperature(data.getTemperature());
             sd.setTimestamp(LocalDateTime.now());
             sdr.save(sd);
             
             //To send trigger alerts
             sd.getSensor_data_id();
             sd.getPressure();
             sd.getTemperature();
             sdr.save(sd);
             Asset asset = apr.findById(sd.getAsset_id()).orElseThrow();
             if(asset.getThresholdPressure()<sd.getPressure()) {
             	alertService.createAlert(asset.getAsset_id(), AlertType.valueOf("PRESSURE_HIGH"), "Pressure is too high , Try with different value.");
        
             }else if(asset.getThresholdTemp()<sd.getTemperature()) {
             	alertService.createAlert(asset.getAsset_id(), AlertType.valueOf("TEMP_HIGH"), "Try entering lower temperature");	
             	
             }
        	 
         }
           
           
    
            return true;
       
    }
}