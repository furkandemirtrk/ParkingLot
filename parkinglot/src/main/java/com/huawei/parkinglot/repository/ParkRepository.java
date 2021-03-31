package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park,Long >{
  /**
   *
   * @param vehicle
   * @return
   */
  Park findFirstByVehicleAndAndEndDateIsNullOrderByStartDate(Vehicle vehicle);

  /**
   *
   * @param parkingArea
   * @return
   */
  int countParkByEndDateIsNullAndParkingArea(ParkingArea parkingArea);
}
