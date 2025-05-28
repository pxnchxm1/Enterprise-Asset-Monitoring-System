package com.eams.service;
import com.eams.entity.AlertType;
import com.eams.entity.Asset;
import com.eams.entity.SensorData;
import com.eams.repository.AssetRepository;
import com.eams.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    // get asset_id and store in asset
    public Optional<SensorData> getSensorDatabyAssetID(Long asset_id) {
        Asset asset = apr.findById(asset_id).orElseThrow();
        Optional<SensorData> sensorData = Optional.of(sdr.findAll().stream().filter(data -> data.getAsset_id().equals(asset.getAsset_id())).findFirst().orElseThrow());
        return sensorData;
    }
    public Boolean sendSensorData(SensorData data){
        try {
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
            
            
            return true;
        }catch (Exception e){
            System.out.println("Error Sending SensorData !!!");
            return false;
        }
    }
}
