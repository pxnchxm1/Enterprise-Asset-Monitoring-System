package com.eams.controller;
import java.util.List;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eams.entity.UptimeLog;
import com.eams.service.UptimeLogService;


@Slf4j
//Marking this class as REST CONTROLLER. It returns JSON/XML
@RestController

//Mapping URL for end points in controller
@RequestMapping("/api/uptime")

//Enables validation
@Validated
public class UptimeLogController {
	
	//Dependency Injection - Automatically injects objects of UptimeLogService
	@Autowired 
	private UptimeLogService service;
	
	//End point to create new UptimeLog entry 
	@PostMapping("/asset/{asset_id}")
	public ResponseEntity<UptimeLog> log(
			
			//Getting asset_id from URL
			@PathVariable Long asset_id,
			
			@RequestBody @Valid UptimeLog log
	){
		return ResponseEntity.ok(service.createlog(asset_id, log));
	}
	
	//End point to get logs for the given asset
	@GetMapping("/getAssets/{asset_id}")
	public List<UptimeLog> getByAsset(@PathVariable Long asset_id){

		return service.getLogs(asset_id);
	}
	
	//End point to get all logs
	@GetMapping
	public List<UptimeLog> getAllLogs(){
        log.info("successfully retrieved all logs !");
		return service.getAllLogs();
	}
}
