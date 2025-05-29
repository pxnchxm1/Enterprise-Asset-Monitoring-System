package com.eams.service;
import java.util.List;

import com.eams.entity.UptimeLog;


public interface UptimeLogServiceInterface {

	
	public UptimeLog createlog(Long asset_id, UptimeLog log);	
	public List<UptimeLog> getLogs(Long asset_id);
	public List<UptimeLog> getAllLogs();

}
