package com.eams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.eams.entity.Alert;
import com.eams.service.AlertService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alerts")
@Validated
public class AlertController {
	@Autowired
	private AlertService service;
	@GetMapping
	public ResponseEntity<List<Alert>> getAllAlerts(){
		return ResponseEntity.ok(service.getAll());
	}
	@PutMapping("/{id}/resolve")
	public ResponseEntity<Alert> resolveAlert(@PathVariable Long id){
		Alert alert = service.resolveAlert(id);
		return alert != null ? ResponseEntity.ok(alert) : ResponseEntity.notFound().build();
	}
	//get assset id ,type of alert and message
	@PostMapping("/send-alert")
	public ResponseEntity<Alert> createAlert(@Valid @RequestBody Alert alert){
		return ResponseEntity.ok(service.createAlert(alert.getAsset_id(),alert.getType(),alert.getMessage()));
	}
	
}
