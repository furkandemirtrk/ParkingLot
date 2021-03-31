package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Long>{
  /**
   *
   * @param name
   * @return
   */
  ParkingArea findByName(String name);

  @Query(value = "select sum(p.fee) from Park p where to_date(p.end_Date,'YYYY-MM-DD') = to_date(:endDate,'YYYY-MM-DD') and p.parking_Area_id = :parkingAreaId ", nativeQuery = true)
  Double dailyIncomeOfTheParkingLot(@Param("endDate") Date endDate, @Param("parkingAreaId") Long parkingAreaId);
}
