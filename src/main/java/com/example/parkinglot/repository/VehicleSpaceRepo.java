package com.example.parkinglot.repository;

import com.example.parkinglot.model.VehicleSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleSpaceRepo extends JpaRepository<VehicleSpace, Long> {

    List<VehicleSpace> findVehicleSpaceBySlotType(String vehicleSpace);
    List<VehicleSpace> findVehicleSpaceBySlotTypeAndEmpty(String vehicleSpace, boolean isEmpty);
}
