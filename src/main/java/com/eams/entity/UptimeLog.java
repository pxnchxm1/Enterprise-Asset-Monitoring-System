package com.eams.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  //Makes the class Entity - maps to a table in database
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UptimeLog {
	 
	@Id  //Makes the field as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Auto generates the primary key
	@Column(unique=true) //Ensuring column is unique in DB
	private Long uptimelog_id;
	
	@ManyToOne
	@JoinColumn(name="asset_id", nullable=false)
	
	//Validation : Asset must not be null
	@NotNull(message = "Data cannot be null")
	private Asset asset;
	
	private LocalDateTime startTime;
	
	//Validation : End time must be not null
	@NotNull(message = "Data cannot be null")
	private LocalDateTime endTime;
	
	//Stores enum values
	@Enumerated(EnumType.STRING)
	private UpTimeLogStatus uptimeLogStatus;
}
