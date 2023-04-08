package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Parking Area Data Transfer Object")
public class ParkingAreaDto{
  @ApiModelProperty(required = true, value = "Name")
  @NotNull
  private String name;
  @ApiModelProperty(required = true, value = "Capacity")
  @NotNull
  @Min(value = 1)
  private Integer capacity;
  @ApiModelProperty(required = true, value = "City")
  @NotNull
  private CityDto city;
  @ApiModelProperty(required = true, value = "Price List")
  @NotNull
  private List<PriceDto> priceList;

}
