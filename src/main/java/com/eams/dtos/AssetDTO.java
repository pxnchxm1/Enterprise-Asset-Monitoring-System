package com.eams.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssetDTO {
	
	@NotNull(message="Name can't be null")
	private String asset_name;
	@NotNull(message="Type can't be null")
	private String asset_type;
	@NotNull(message="Location can't be null")
	private String location;
	@NotNull(message="Temp can't be null")
	private double thresholdTemp;
	@NotNull(message="Pressure can't be null")
	private double thresholdPressure;
	@NotNull(message="AssignedTo can't be null")
	private Long assignedTo;

}
