package com.example.parkinglot.controller;


import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.model.VehicleSpace;
import com.example.parkinglot.repository.VehicleRepo;
import com.example.parkinglot.repository.VehicleSpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles/")
public class VehicleController
{

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleSpaceRepo vehicleSpaceRepo;

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @PostMapping("/getTicket")
    public String getTicket(@RequestBody Vehicle vehicle)
    {

        Optional<VehicleSpace> temp_vehicleSpace=vehicleSpaceRepo.findById(vehicle.getSlotNo());

        //checking error cases
        if(!temp_vehicleSpace.isPresent())
            return "Invalid Slot Number";
        else if(temp_vehicleSpace.get().getSlotType()!=vehicle.getVehicleType())
            return "The slot is not meant for the vehicle";
        else if(!temp_vehicleSpace.get().isEmpty())
            return "The slot is not empty";

        //saving space
        VehicleSpace vehicleSpace=new VehicleSpace(vehicle.getSlotNo(),vehicle.getVehicleNo(),vehicle.getVehicleType(),false);
        vehicleSpaceRepo.save(vehicleSpace);

        //saving vehicle
        vehicleRepo.save(vehicle);

        return "The Vehicle "+vehicle.getVehicleNo()+"entered successfully";

    }








}
