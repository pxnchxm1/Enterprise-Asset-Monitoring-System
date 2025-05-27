package com.eams.dto;

import lombok.Data;

@Data
public class AssetDTO {
	
	private String asset_name;
	private String asset_type;
	private String location;
	private double thresholdTemp;
	private double thresholdPressure;
	private Long assignedTo;

}
