package com.eams.controller;

import com.eams.entity.MaintenanceLog;
import com.eams.service.MaintenanceLogService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-log")
@Validated
public class MaintenanceLogController {

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceLogController.class);

    @Autowired
    private MaintenanceLogService service;

    // Schedule a maintenance log for an asset
    @PostMapping("/schedule/{assetId}")
    public ResponseEntity<MaintenanceLog> scheduleLog(@PathVariable Long assetId, @RequestBody @Valid MaintenanceLog log) {
        logger.info("Scheduling maintenance log for asset ID: {}", assetId);
        try {
            MaintenanceLog scheduledLog = service.schedule(assetId, log);
            logger.info("Maintenance log scheduled successfully for asset ID: {}", assetId);
            return ResponseEntity.ok(scheduledLog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<MaintenanceLog>> getByAssetId(@PathVariable Long assetId) {
        logger.info("Fetching maintenance logs for asset ID: {}", assetId);
        List<MaintenanceLog> logs = service.getByAssetId(assetId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceLog>> getAllLogs() {
        logger.info("Fetching all maintenance logs");
        List<MaintenanceLog> logs = service.getAllAssetId();
        return ResponseEntity.ok(logs);
    }
}
