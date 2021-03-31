package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@ApiModel("Parking Area Find By Name Request Object")
public class ParkingAreaFindByNameRequest{
  @ApiModelProperty(required = true, value = "Name")
  @NotNull
  private String name;
}
