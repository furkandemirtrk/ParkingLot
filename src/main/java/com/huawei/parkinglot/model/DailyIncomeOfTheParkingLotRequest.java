package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@ApiModel("Daily Income Of The Parking Lot Request Object")
public class DailyIncomeOfTheParkingLotRequest{

  @ApiModelProperty(required = true, value = "Date", example = "2021-03-30")
  @NotNull
  private Date date;

  @ApiModelProperty(required = true, value = "Parking Area Id")
  @NotNull
  private Long parkingAreaId;
}
