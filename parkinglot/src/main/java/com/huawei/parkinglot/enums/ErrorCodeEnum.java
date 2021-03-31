package com.huawei.parkinglot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCodeEnum{

  INTERNAL_SERVER_ERROR(1000, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
  FIELD_VALIDATION_ERROR(1001, "Field validation error.", HttpStatus.BAD_REQUEST),
  CONTENT_NOT_FOUND_ERROR(1002, "Content not found.", HttpStatus.BAD_REQUEST),
  VEHICLE_TYPE_ERROR(1003, "Wrong Vehicle Type.", HttpStatus.BAD_REQUEST),
  PARKING_LOT_IS_FULL(1004, "The parking lot is full.", HttpStatus.BAD_REQUEST),
  THERE_IS_VEHICLE_IN_THE_PARKING_LOT(1005, "There is  car in the parking area.", HttpStatus.BAD_REQUEST);

  private int code;
  private String message;
  private HttpStatus httpStatus;
}
