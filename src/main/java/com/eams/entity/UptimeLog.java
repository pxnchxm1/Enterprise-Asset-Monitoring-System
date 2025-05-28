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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UptimeLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private Long uptimelog_id;
	
	@ManyToOne
	@JoinColumn(name="asset_id", nullable=false)
	private Asset asset;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Enumerated(EnumType.STRING)
	private UpTimeLogStatus uptimeLogStatus;
}
