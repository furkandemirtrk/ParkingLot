package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParkRepository extends JpaRepository<Park,Long >{
  /**
   * Find First By Vehicle And End Date Is Null Order By Start Date
   * @param vehicle
   * @return
   */
  Park findFirstByVehicleAndEndDateIsNullOrderByStartDate(Vehicle vehicle);

  /**
   *
   * @param parkingArea
   * @return
   */
  int countParkByEndDateIsNullAndParkingArea(ParkingArea parkingArea);

  /**
   * Find All By Parking Area
   *
   * @param parkingArea
   * @return
   */
  List<Park> findAllByParkingArea(ParkingArea parkingArea);
}
