package com.eams.controller;
import com.eams.entity.MaintenanceLog;
import com.eams.service.MaintenanceLogService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-log")
@Validated
public class MaintenanceLogController {

    @Autowired
    private MaintenanceLogService service;

    // Schedule a maintenance log for an asset
    @PostMapping("/schedule/{assetId}")
    public ResponseEntity<MaintenanceLog> scheduleLog(@PathVariable Long assetId, @RequestBody @Valid MaintenanceLog log) {
        try {
            MaintenanceLog scheduledLog = service.schedule(assetId, log);
            return ResponseEntity.ok(scheduledLog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<MaintenanceLog>> getByAssetId(@PathVariable Long assetId) {
        try {
            List<MaintenanceLog> logs = service.getByAssetId(assetId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceLog>> getAllLogs() {
        List<MaintenanceLog> logs = service.getAllAssetId();
        return ResponseEntity.ok(logs);
    }
}
