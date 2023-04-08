package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,String>{
  /**
   *
   * @param licensePlate
   * @return Vehicle
   */
  Vehicle findByLicensePlate(String licensePlate);
}
