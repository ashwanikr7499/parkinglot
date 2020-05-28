package com.example.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity @AllArgsConstructor @NoArgsConstructor
@Table(name = "vehicleSpace")
//entity listener
public class VehicleSpace {

    @Id @Getter @Setter @Column(name = "slotNo", nullable = false)
    private long slotNo;

    @Getter @Setter @Column(name = "vehicleNo", nullable = false)
    private String vehicleNo;

    @Getter @Setter @Column(name = "slotType", nullable = false)
    private String slotType;


    @Getter @Setter @Column(name = "isEmpty", nullable = false)
    private boolean isEmpty;

}
