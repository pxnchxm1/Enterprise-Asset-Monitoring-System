package com.eams.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor

public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Long asset_id;
	
	private String message;
	
	private LocalDateTime triggeredAt=LocalDateTime.now();
}
