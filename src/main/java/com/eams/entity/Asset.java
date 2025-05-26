package com.eams.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asset_id;

    private String asset_name;

    private String asset_type;

    private String location;

    private double thresholdTemp;

    private double thresholdPressure;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo; 
}
