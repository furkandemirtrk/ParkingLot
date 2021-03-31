package com.huawei.parkinglot.service;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.model.CheckInRequest;
import com.huawei.parkinglot.model.VehicleDto;
import org.springframework.stereotype.Service;


@Service
public interface ParkService{
  /**
   *
   * @param checkInRequest
   * @return
   * @throws ParkingLotException
   */
  Park checkIn(CheckInRequest checkInRequest) throws ParkingLotException;

  /**
   *
   * @param park
   * @return
   */
  Double calculateFee(Park park);

  /**
   *
   * @param vehicleDto
   * @return
   * @throws ParkingLotException
   */
  public Park parkingProcess(VehicleDto vehicleDto) throws ParkingLotException;
}
