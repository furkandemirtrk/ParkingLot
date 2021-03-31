package com.huawei.parkinglot.model;


import com.huawei.parkinglot.enums.VehicleType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ApiModel("Check-In Request Object")
public class CheckInRequest{
  @NotNull
  private VehicleType vehicleType;
  @NotNull
  private String licensePlate;
  @NotNull
  private Long parkingAreaId;

}
