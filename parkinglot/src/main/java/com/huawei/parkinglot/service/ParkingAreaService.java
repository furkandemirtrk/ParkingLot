package com.huawei.parkinglot.service;

import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.model.DailyIncomeOfTheParkingLotRequest;
import com.huawei.parkinglot.model.ParkingAreaDto;
import com.huawei.parkinglot.model.ParkingAreaFindByNameRequest;


public interface ParkingAreaService{
  /**
   * Save Parking Area
   * @param parkingAreaDto
   * @return
   * @throws ParkingLotException
   */
  ParkingAreaDto save(ParkingAreaDto parkingAreaDto) throws ParkingLotException;

  /**
   * Update Parking Area
   * @param parkingAreaDto
   * @return
   */
  ParkingAreaDto update(Long id,ParkingAreaDto parkingAreaDto) throws ParkingLotException;

  /**
   * Delete Parking Area
   * @param id
   * @return
   * @throws ParkingLotException
   */
  Boolean delete(Long id) throws ParkingLotException;

  /**
   * Parking Area Find By Name
   * @param parkingAreaFindByNameRequest
   * @return
   * @throws ParkingLotException
   */
  ParkingAreaDto findByName(ParkingAreaFindByNameRequest parkingAreaFindByNameRequest) throws ParkingLotException;

  /**
   * Daily Income Of The Parking Lot
   * @param dailyIncomeOfTheParkingLotRequest
   * @return
   * @throws ParkingLotException
   */
  Double dailyIncomeOfTheParkingLot(DailyIncomeOfTheParkingLotRequest dailyIncomeOfTheParkingLotRequest) throws ParkingLotException;
}
