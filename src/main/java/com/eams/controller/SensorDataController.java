package com.eams.controller;
import com.eams.entity.SensorData;
import com.eams.service.SensorDataService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/sensors")
@Validated
public class SensorDataController {
	@Autowired
    private  SensorDataService SDService;

    
    public SensorDataController(SensorDataService sensorDataService) {
        this.SDService = sensorDataService;
    }

    // This function gets asset_id
    @GetMapping("/asset/{asset_id}")
    public ResponseEntity<Optional<SensorData>> getSensorData(@PathVariable Long asset_id) {
        return ResponseEntity.ok(SDService.getSensorDatabyAssetID(asset_id));
    }
    @PostMapping("/send-data")
    public String sendSensorData(@Validated @RequestBody SensorData sd){
        if(SDService.sendSensorData(sd)){
            return "Successfully Sent SensorData";
        }else{
            return "Failed To send SensorData !";
        }
    }
}