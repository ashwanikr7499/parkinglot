package com.example.parkinglot.controller;


import com.example.parkinglot.model.Vehicle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles/2/")
public class TwoWheelerController extends  VehicleController {

    @GetMapping("/beforeTime/{time}")
    public List<Vehicle> getVehicleBeforeTime(@PathVariable(value = "time") String time)
    {
        return vehicleRepo.findVehiclesByTimeBeforeAndVehicleType(time,"2");
    }

    @GetMapping("/afterTime/{time}")
    public List<Vehicle> getVehicleAfterTime(@PathVariable(value = "time") String time)
    {
        return vehicleRepo.findVehiclesByTimeAfterAndVehicleType(time,"2");
    }
    @GetMapping("/beforeTime/{time}")
    public List<Vehicle> getVehicleSortedByTime(@PathVariable(value = "time") String time)
    {
        return vehicleRepo.findVehiclesByVehicleTypeOrderByTimeTimeAsc(time,"2");
    }


}
