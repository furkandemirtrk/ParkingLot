package com.huawei.parkinglot.controller;

import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.enums.ErrorCodeEnum;
import com.huawei.parkinglot.model.DailyIncomeOfTheParkingLotRequest;
import com.huawei.parkinglot.model.ParkingAreaDto;
import com.huawei.parkinglot.model.ParkingAreaFindByNameRequest;
import com.huawei.parkinglot.service.ParkingAreaService;
import com.huawei.parkinglot.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(ApiPaths.ParkingAreaController.CONTROLLER)
@Api(value = ApiPaths.ParkingAreaController.CONTROLLER, tags = { "Parking Area APIs" })
@RequiredArgsConstructor
public class ParkingAreaController{
  private final ParkingAreaService parkingAreaService;

  @ApiOperation(value = "Create Parking Area", response = ParkingAreaDto.class)
  @PostMapping
  public ResponseEntity<ParkingAreaDto> createParkingArea(@Valid  @RequestBody ParkingAreaDto parkingAreaDto) throws ParkingLotException{
    log.info("ParkingAreaController createParkingArea start");
    return ResponseEntity.ok(parkingAreaService.save(parkingAreaDto));
  }

  @ApiOperation(value = "Update Parking Area", response = ParkingAreaDto.class)
  @PutMapping("/{id}")
  public ResponseEntity<ParkingAreaDto> updateParkingArea(@PathVariable Long id,@Valid  @RequestBody ParkingAreaDto parkingAreaDto) throws ParkingLotException{
    log.info("ParkingAreaController updateParkingArea start for id {}", id);
    if (id == null){
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    return ResponseEntity.ok(parkingAreaService.update(id,parkingAreaDto));
  }

  @ApiOperation(value = "Delete Parking Area", response = Boolean.class)
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteParkingArea(@PathVariable Long id) throws ParkingLotException{
    log.info("ParkingAreaController deleteParkingArea start for id{}", id);
    if (id == null){
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    return ResponseEntity.ok(parkingAreaService.delete(id));
  }

  @ApiOperation(value = "Find By Name Parking Area", response = ParkingAreaDto.class)
  @PostMapping(ApiPaths.ParkingAreaController.FIND_BY_NAME)
  public ResponseEntity<ParkingAreaDto> findByName(@Valid  @RequestBody ParkingAreaFindByNameRequest parkingAreaFindByNameRequest) throws ParkingLotException{
    log.info("ParkingAreaController findByName start ");
    if (parkingAreaFindByNameRequest == null || parkingAreaFindByNameRequest.getName().equals("")){
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    return ResponseEntity.ok(parkingAreaService.findByName(parkingAreaFindByNameRequest));
  }

  @ApiOperation(value = "Daily income of the parking lot", response = Double.class)
  @PostMapping(ApiPaths.ParkingAreaController.DAILY_INCOME)
  public ResponseEntity<Double> dailyIncomeOfTheParkingLot(@Valid  @RequestBody DailyIncomeOfTheParkingLotRequest dailyIncomeOfTheParkingLotRequest) throws ParkingLotException{
    log.info("ParkingAreaController dailyIncomeOfTheParkingLot start ");
    Double d = parkingAreaService.dailyIncomeOfTheParkingLot(dailyIncomeOfTheParkingLotRequest);
    return ResponseEntity.ok(d);
  }
}
