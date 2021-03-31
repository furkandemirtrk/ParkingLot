package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "City Data Transfer Object")
public class CityDto{
  @ApiModelProperty(required = true, value = "ID")
  private Long id;
}
