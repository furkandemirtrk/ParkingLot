package com.huawei.parkinglot.exception;

import com.huawei.parkinglot.enums.ErrorCodeEnum;
import lombok.Getter;


public class ParkingLotException extends Exception{
  @Getter
  private final ErrorCodeEnum errorCodeEnum;

  public ParkingLotException(ErrorCodeEnum errorCodeEnum){
    this.errorCodeEnum = errorCodeEnum;
  }
}
