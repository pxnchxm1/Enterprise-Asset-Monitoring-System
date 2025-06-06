package com.eams.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
	
	//Sensor_data_id is primary key and auto generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensor_data_id;

    // asset_id is foreign key 
    @Column(name = "asset_id", nullable = false)
    private Long asset_id;
    
    @Column(name = "temperature",nullable = false)
    private Double temperature;
    
    @Column(name = "pressure",nullable = false)
    private Double pressure;
    
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp = LocalDateTime.now();
}