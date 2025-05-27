package com.eams.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eams.entity.UptimeLog;
import com.eams.service.UptimeLogService;

@RestController
@RequestMapping("/api/uptime")
public class UptimeLogController {

	@Autowired private UptimeLogService service;
	@PostMapping
	public ResponseEntity<UptimeLog> log(
			@RequestParam Long asset_id,
			@RequestBody UptimeLog log
	){
		return ResponseEntity.ok(service.logStatus(asset_id, log));
	}
	@GetMapping("/asset/{id}")
	public List<UptimeLog> getByAsset(@PathVariable Long id){
		return service.getLogs(id);
	}
}
