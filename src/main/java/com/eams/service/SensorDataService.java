package com.eams.service;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.entity.SensorData;
import com.eams.exception.InvalidSensorDataException;
import com.eams.repository.AssetRepository;
import com.eams.repository.SensorDataRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SensorDataService {
    private SensorDataRepository sdr;
    
    private AssetRepository apr;
    
    private AlertService alertService;
    
    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository,AssetRepository assetRepo, AlertService as) {
        this.sdr = sensorDataRepository;
        this.apr = assetRepo;
        this.alertService=as;
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
         }if(data.getTemperature()<0) {
         	
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
             log.info("Sensor data saved with pressure: {}, temperature: {}", sd.getPressure(), sd.getTemperature());
             Asset asset = apr.findById(sd.getAsset_id()).orElseThrow();
             if(asset.getThresholdPressure()<sd.getPressure()) {
            	 log.warn("created alert for high pressure !!! ");
             	alertService.createAlert(asset.getAsset_id(), AlertType.valueOf("PRESSURE_HIGH"), "Pressure is too high , Try with different value.");
        
             }if(asset.getThresholdTemp()<sd.getTemperature()) {
            	 log.warn("created alert for high temperature !!! ");
             	alertService.createAlert(asset.getAsset_id(), AlertType.valueOf("TEMP_HIGH"), "Try entering lower temperature");	
             	
             }
        	 
         }
           
           
    
            return true;
       
    }
}