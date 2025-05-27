package com.eams.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long maintanance_log_id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset_id; 
    private LocalDate scheduledDate;

    private LocalDate completedDate;

    private String remarks;
}
