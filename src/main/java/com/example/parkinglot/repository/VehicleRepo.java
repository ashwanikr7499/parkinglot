package com.example.parkinglot.repository;

import com.example.parkinglot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {
}
