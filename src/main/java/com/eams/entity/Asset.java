package com.eams.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Asset {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long asset_id;
	private String asset_name;
	private String asset_type;
	private String asset_location;
	private double asset_thresholdTemp;
	private double asset_thresholdPressure;
	
	@ManyToOne
	@JoinColumn(name = 'user_id')
	private User assignedTo;
}
