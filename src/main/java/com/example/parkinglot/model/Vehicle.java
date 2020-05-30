package com.example.parkinglot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;

@Entity @AllArgsConstructor @NoArgsConstructor
@Table(name = "vehicle")

public class Vehicle {

    @Id @Getter @Setter
    @Column(name = "vehicleNo", nullable = false)
    private String vehicleNo;

    @Getter @Setter @Column(name = "slotNo", nullable = false)
    private long slotNo;

    @Getter @Setter @Column(name = "vehicleType", nullable = false)
    private String vehicleType;

    @Getter @Setter @Column(name = "time", nullable = false)
    private String time;

    @Getter @Setter @Column(name = "colour", nullable = false)
    private String colour;
}

