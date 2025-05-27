package com.eams.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@AllArgsConstructer
@NoArgsConstructer

public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "asset_id")
	@Column(unique=true)
	private Asset asset_id;
	
	private String message;
	
	private LocalDateTime triggeredAt=LocalDateTime.now();
}
