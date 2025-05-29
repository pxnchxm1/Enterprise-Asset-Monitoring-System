package com.eams.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
	private Long id;
	
	@JoinColumn(name = "asset_id")
	private Long asset_id;
	
	@NotBlank(message = "Message is required")
	@Size(max = 250, message = "Message too long")
	private String message;
	
	private LocalDateTime triggeredAt=LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private AlertType type;
	
	@Enumerated(EnumType.STRING)
	private AlertStatus status = AlertStatus.ACTIVE;
}