package com.example.parkinglot.repository;

import com.example.parkinglot.model.VehicleSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleSpaceRepo extends JpaRepository<VehicleSpace, Long> {

    List<VehicleSpace> findVehiclesSpaceBySlotType(String vehicleSpace);
    List<VehicleSpace> findVehiclesSpaceBySlotTypeAndEmpty(String vehicleSpace, boolean isEmpty);
}
