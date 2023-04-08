package com.huawei.parkinglot.entity.vehicle;

import com.huawei.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue(value = VehicleType.Values.SUV)
public class SUV extends Vehicle {


  public SUV(String licensePlate){
    this.setLicensePlate(licensePlate);
  }

  public static final Double MULTIPLIER = 0.10;

  @Override
  public Double getMultiplier(Double fee){
    return fee + (fee * MULTIPLIER);
  }
}
