package com.example.parkinglot.controller;


import com.example.parkinglot.Input.InputData;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.model.VehicleSpace;
import com.example.parkinglot.repository.VehicleRepo;
import com.example.parkinglot.repository.VehicleSpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController
{

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleSpaceRepo vehicleSpaceRepo;

    int[] cost_perh ={5,10,15,20};
    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }


    @GetMapping("/beforeTime/{time}")
    public List<Vehicle> getVehicleBeforeTime(@PathVariable(value = "time") String time)
    {
        return vehicleRepo.findVehiclesByTimeBefore(time);
    }


    @GetMapping("/afterTime/{time}")
    public List<Vehicle> getVehicleAfterTime(@PathVariable(value = "time") String time)
    {
        return vehicleRepo.findVehiclesByTimeAfter(time);
    }

    @GetMapping("/{vehicleType}")
    public List<Vehicle> getVehiclesByType(@PathVariable(value = "vehicleType") String vehicleType)
    {
        return vehicleRepo.findVehiclesByVehicleType(vehicleType);
    }

    @PostMapping("/getTicket")
    public String getTicket(@RequestBody Vehicle vehicle)
    {

        Optional<VehicleSpace> temp_vehicleSpace=vehicleSpaceRepo.findById(vehicle.getSlotNo());

        //checking error cases
        if(!temp_vehicleSpace.isPresent())
            return "Invalid Slot Number";
        else if(!temp_vehicleSpace.get().getSlotType().equals(vehicle.getVehicleType()))
            return "The slot is not meant for the vehicle";
        else if(!temp_vehicleSpace.get().isEmpty())
            return "The slot is not empty";

        //saving space
        VehicleSpace vehicleSpace=new VehicleSpace(vehicle.getSlotNo(),vehicle.getVehicleNo(),vehicle.getVehicleType(),false);
        vehicleSpaceRepo.save(vehicleSpace);

        //saving vehicle
        vehicleRepo.save(vehicle);

        return "The Vehicle "+vehicle.getVehicleNo()+" entered successfully";

    }

    @DeleteMapping("/exit")
    public String exit(@RequestBody InputData inputData) throws Exception
    {
        String outTime=inputData.getTime();


        //checking whether the vehicle entered
        Optional<Vehicle> temp_vehicle=vehicleRepo.findById(inputData.getVehicleNo());
        if(!temp_vehicle.isPresent())
            return "Vehicle is not among registered entered vehicles";

        //calculating parking fee
        String inTime=temp_vehicle.get().getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1=format.parse(inTime);
        Date date2=format.parse(outTime);
        float diff=(date2.getTime()-date1.getTime())/3600000.0f;
        float cost=diff*cost_perh[vehicleTypeToInt(temp_vehicle.get().getVehicleType())];
        //creating space
        VehicleSpace vehicleSpace=new VehicleSpace(temp_vehicle.get().getSlotNo(),temp_vehicle.get().getVehicleNo(),temp_vehicle.get().getVehicleType(),true);
        vehicleSpaceRepo.save(vehicleSpace);

        //deleting vehicle
        vehicleRepo.delete(temp_vehicle.get());

        return "Vehicle "+inputData.getVehicleNo()+" exited successfully\nParking fee = ₹"+cost;
    }



    public int vehicleTypeToInt(String vehicleType)
    {
        if(vehicleType.equals("2")) return 0;
        if(vehicleType.equals("3")) return 1;
        if(vehicleType.equals("4")) return 2;
        return 3;
    }






}
