package com.example.parkinglot.repository;

import com.example.parkinglot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {

    List<Vehicle> findVehiclesByTimeBeforeAndVehicleType(String time,String vehicleType);
    List<Vehicle> findVehiclesByTimeAfterAndVehicleType(String time,String vehicleType);
    List<Vehicle> findVehiclesByTimeBefore(String time);
    List<Vehicle> findVehiclesByTimeAfter(String time);
    List<Vehicle> findVehiclesByVehicleTypeOrderByTimeAsc(String vehicleType);
}

