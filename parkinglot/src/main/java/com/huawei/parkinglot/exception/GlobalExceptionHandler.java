package com.huawei.parkinglot.exception;

import com.huawei.parkinglot.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request){  //NOSONAR
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setDate(new Date());
    if (ex instanceof ParkingLotException){
      errorResponse.setCode(((ParkingLotException) ex).getErrorCodeEnum().getCode());
      errorResponse.setMessage(((ParkingLotException) ex).getErrorCodeEnum().getMessage());
      log.error("ParkingLot Error", ex);
    } else {
      errorResponse.setMessage(ex.getMessage());
      errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      log.error("INTERNAL_SERVER_ERROR Error", ex);
    }
    return new ResponseEntity<>(errorResponse , HttpStatus.EXPECTATION_FAILED);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorResponse exceptionResponse = new ErrorResponse(1001,ex.getBindingResult().toString(), new Date());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }
}
