package com.huawei.parkinglot.model;

import com.huawei.parkinglot.enums.VehicleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ApiModel(value = "Vehicle Data Transfer Object")
public class VehicleDto{
  @ApiModelProperty(required = true, value = "Vehicle Type")
  @NotNull
  private VehicleType type;
  @NotNull
  private String licensePlate;
}
