package com.example.parkinglot.controller;

import com.example.parkinglot.model.VehicleSpace;
import com.example.parkinglot.repository.VehicleSpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class VehicleSpaceController {

    @Autowired
    VehicleSpaceRepo vehicleSpaceRepo;

    @PostConstruct
    public void initData()
    {
        for(long i=1;i<=4;i++)
        {
            VehicleSpace vehicleSpace_2=new VehicleSpace(i,"none","2",true);
            vehicleSpaceRepo.save(vehicleSpace_2);

            VehicleSpace vehicleSpace_3=new VehicleSpace(10+i,"none","3",true);
            vehicleSpaceRepo.save(vehicleSpace_3);

            VehicleSpace vehicleSpace_4=new VehicleSpace(20+i,"none","4",true);
            vehicleSpaceRepo.save(vehicleSpace_4);

            VehicleSpace vehicleSpace_heavy=new VehicleSpace(30+i,"none","heavy",true);
            vehicleSpaceRepo.save(vehicleSpace_heavy);
        }
    }

    @GetMapping("/")
    public List<VehicleSpace> getAllSlots()
    {
        return vehicleSpaceRepo.findAll();
    }

    @GetMapping("/{slotType}")
    public List<VehicleSpace> getAllSlotsByType(@PathVariable(value = "slotType") String slotType)
    {
        return vehicleSpaceRepo.findVehicleSpacesBySlotType(slotType);
    }
    @GetMapping("/{slotType}/empty")
    public List<VehicleSpace> getEmptySlotsByType(@PathVariable(value = "slotType") String slotType)
    {
        return vehicleSpaceRepo.findVehicleSpacesByVehicleNoAndSlotType("none",slotType);
    }

}

