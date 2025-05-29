package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.eams.entity.Alert;
import com.eams.service.AlertService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/alerts")
@Validated
@Slf4j
public class AlertController {
	@Autowired
	private AlertService service;
	//endpoint to fetch all alerts
	@GetMapping
	public ResponseEntity<List<Alert>> getAllAlerts(){
		log.info("Fetching all alerts");
		return ResponseEntity.ok(service.getAll());
	}
	//endpoint to resolve a specific alert
	@PutMapping("/{id}/resolve")
	public ResponseEntity<Alert> resolveAlert(@PathVariable Long id){
		log.info("Resolving alert with id");
		Alert alert = service.resolveAlert(id);
		return alert != null ? ResponseEntity.ok(alert) : ResponseEntity.notFound().build();
	}
	//get assset id ,type of alert and message
	@PostMapping("/send-alert")
	public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert){
		log.info("Creating alert for asset_id, type, message");
		return ResponseEntity.ok(service.createAlert(alert.getAsset_id(),alert.getType(),alert.getMessage()));
	}
	
}
