package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Price Data Transfer Object")
public class PriceDto{
  @ApiModelProperty(required = true, value = "Start Hour")
  @NotNull
  @Min(value = 0) @Max(value = 23)
  private Integer startHour;
  @ApiModelProperty(required = true, value = "End Hour")
  @NotNull
  @Min(value = 1) @Max(value = 24)
  private Integer endHour;
  @ApiModelProperty(required = true, value = "Fee")
  @NotNull
  @Min(value = 0)
  private Integer fee;

}
