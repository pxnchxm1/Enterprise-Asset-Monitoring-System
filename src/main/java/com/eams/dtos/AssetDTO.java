package com.eams.dtos;

import com.eams.entity.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
	
	@NotNull(message="Name can't be null")
	private String asset_name;
	@NotNull(message="Type can't be null")
	private String asset_type;
	@NotNull(message="Location can't be null")
	private String location;
	@NotNull(message="Temp can't be null")
    @Min(value = 1, message = "Threshold temperature must be at least 1")
    @Max(value = 200, message = "Threshold temperature must not exceed 200")
	private double thresholdTemp;
	@NotNull(message="Pressure can't be null")
    @Min(value = 1, message = "Threshold pressure must be at least 1")
    @Max(value = 300, message = "Threshold pressure must not exceed 300")
	private double thresholdPressure;
	@NotNull(message="AssignedTo can't be null")
	private User assignedTo;
	
	
}
