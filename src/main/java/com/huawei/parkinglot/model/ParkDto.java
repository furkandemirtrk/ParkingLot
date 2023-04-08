package com.huawei.parkinglot.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Park Data Transfer Object")
public class ParkDto{
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Double fee;
}
