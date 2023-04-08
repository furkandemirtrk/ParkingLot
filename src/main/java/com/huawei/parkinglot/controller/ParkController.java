package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.factory.VehicleServiceFactory;
import com.huawei.parkinglot.model.CheckInRequest;
import com.huawei.parkinglot.model.ParkDto;
import com.huawei.parkinglot.model.VehicleDto;
import com.huawei.parkinglot.service.ParkService;
import com.huawei.parkinglot.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping(ApiPaths.ParkController.CONTROLLER)
@Api(value = ApiPaths.ParkController.CONTROLLER, tags = { "Park APIs" })
@RequiredArgsConstructor
public class ParkController{

  private final ParkService parkService;
  private final VehicleServiceFactory vehicleServiceFactory;

  @ApiOperation(value = "Check-in Operations", response = Boolean.class)
  @PostMapping("/checkIn")
  public ResponseEntity<LocalDateTime> checkIn(@Valid @RequestBody CheckInRequest checkInRequest) throws ParkingLotException{
    log.info("checkIn operation is started");
    Park park = parkService.checkIn(checkInRequest);
    log.info("checkIn operation is finished");
    return ResponseEntity.ok(park.getStartDate());
  }

  @ApiOperation(value = "Check-out Operations")
  @PostMapping("/checkOut")
  public ResponseEntity<ParkDto> checkOut(@Valid @RequestBody VehicleDto vehicleDto) throws ParkingLotException{
    log.info("checkOut operation is started");
    ParkDto parkDto = vehicleServiceFactory.getVehicleService(vehicleDto.getType().name()).checkOut(vehicleDto);
    log.info("checkOut operation is finished");
    return ResponseEntity.ok(parkDto);
  }
}
