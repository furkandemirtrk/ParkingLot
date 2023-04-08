package com.huawei.parkinglot.factory;

import com.huawei.parkinglot.entity.vehicle.Minivan;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.enums.VehicleType;


public class VehicleFactory{
  private VehicleFactory(){
  }

  public static Vehicle getVehicle(String type, String licensePlate){
    if (VehicleType.Values.SEDAN.equals(type)){
      return new Sedan(licensePlate);
    }else if(VehicleType.Values.MINIVAN.equals(type)){
      return new Minivan(licensePlate);
    }else if(VehicleType.Values.SUV.equals(type)){
      return new SUV(licensePlate);
    }
    return null;
  }
}
