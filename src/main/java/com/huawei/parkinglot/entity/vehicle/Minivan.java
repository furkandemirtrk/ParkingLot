package com.huawei.parkinglot.entity.vehicle;

import com.huawei.parkinglot.enums.VehicleType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue(value = VehicleType.Values.MINIVAN)
public class Minivan extends Vehicle{

  public Minivan(String licensePlate){
    this.setLicensePlate(licensePlate);
  }

  public static final Double MULTIPLIER = 0.15;

  @Override
  public Double getMultiplier(Double fee){
    return fee + (fee * MULTIPLIER);
  }


}
