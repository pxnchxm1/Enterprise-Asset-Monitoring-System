package com.eams.service;
import com.eams.entity.Asset;
import com.eams.entity.SensorData;
import com.eams.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {
    @Autowired
    private SensorDataRepository sdr;
    @Autowired
    private AssetRepository apr;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sdr = sensorDataRepository;
    }
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
//            TODO:Check the temp and press and trigger alert if exceeds thres value
            return true;
        }catch (Exception e){
            System.out.println("Error Sending SensorData !!!");
            return false;
        }
    }
}
