package com.huawei.parkinglot.factory;

import com.huawei.parkinglot.enums.VehicleType;
import com.huawei.parkinglot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class VehicleServiceFactory{

  @Autowired @Qualifier("sedanVehicleService")
  private VehicleService sedanVehicleService;

  @Autowired @Qualifier("suvVehicleService")
  private VehicleService suvVehicleService;

  @Autowired @Qualifier("minivanVehicleService")
  private VehicleService minivanVehicleService;

  public VehicleService getVehicleService(String vehicleType){
    if (VehicleType.Values.SEDAN.equals(vehicleType)){
      return sedanVehicleService;
    } else if(VehicleType.Values.SUV.equals(vehicleType)){
      return suvVehicleService;
    }else if(VehicleType.Values.MINIVAN.equals(vehicleType)){
      return minivanVehicleService;
    }
    return null;
  }

}
