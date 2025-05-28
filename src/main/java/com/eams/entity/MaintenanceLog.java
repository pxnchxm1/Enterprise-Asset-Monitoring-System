package com.eams.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    @Column(name = "asset_id", nullable = false)
    private Long asset_id; 
    
    @NotNull(message = "Date cannot be blank")
    private LocalDate scheduledDate;
    @NotNull(message = "Date cannot be blank")
    private LocalDate completedDate;
    @NotBlank(message = "Remarks cannot be null")
    private String remarks;
}
