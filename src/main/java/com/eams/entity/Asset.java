package com.eams.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String location;
    private double thresholdTemp;
    private double thresholdPressure;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo;
}
