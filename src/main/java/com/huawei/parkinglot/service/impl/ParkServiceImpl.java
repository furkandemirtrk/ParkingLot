package com.huawei.parkinglot.service.impl;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.Price;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.enums.ErrorCodeEnum;
import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.factory.VehicleFactory;
import com.huawei.parkinglot.model.CheckInRequest;
import com.huawei.parkinglot.model.VehicleDto;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import com.huawei.parkinglot.service.ParkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;


@Slf4j
@AllArgsConstructor
@Service
public class ParkServiceImpl implements ParkService{

  private final ParkRepository parkRepository;
  private final VehicleRepository vehicleRepository;
  private final ParkingAreaRepository parkingAreaRepository;


  @Override
  public Park checkIn(CheckInRequest checkInRequest)  throws ParkingLotException{
    log.info("checkIn start {} {} {},", checkInRequest.getLicensePlate(), checkInRequest.getVehicleType(), checkInRequest.getParkingAreaId());
    ParkingArea parkingArea = parkingAreaRepository.findById(checkInRequest.getParkingAreaId()).orElse(null);
    if (parkingArea == null){
      log.error("parking area cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    if (parkRepository.countParkByEndDateIsNullAndParkingArea(parkingArea) >= parkingArea.getCapacity()){
      log.error("parking area is full");
      throw new ParkingLotException(ErrorCodeEnum.PARKING_LOT_IS_FULL);
    }
    Vehicle vehicle = vehicleRepository.findByLicensePlate(checkInRequest.getLicensePlate());
    if (vehicle == null){
      vehicle = createVehicle(checkInRequest.getVehicleType().name(),checkInRequest.getLicensePlate());
    }
    return parkRepository.save(Park.builder().startDate(LocalDateTime.now()).parkingArea(parkingArea).vehicle(vehicle).build());
  }

  private Vehicle createVehicle(String type, String plate) throws ParkingLotException{
    Vehicle saveVehicle = VehicleFactory.getVehicle(type,plate);
    if (saveVehicle != null){
      return vehicleRepository.save(saveVehicle);
    }else {
      log.error("vehicle type error");
      throw new ParkingLotException(ErrorCodeEnum.VEHICLE_TYPE_ERROR);
    }
  }

  @Override
  public Double calculateFee(Park park){
    LocalDateTime tempLocalDateTime = LocalDateTime.from(park.getStartDate());
    long hourDiff = tempLocalDateTime.until(park.getEndDate(), ChronoUnit.HOURS);
    double fee = 0;
    park.getParkingArea().getPriceList().sort(Comparator.comparingInt(Price::getEndHour).reversed());
    while (hourDiff >= 0){
      for(Price price : park.getParkingArea().getPriceList()){
        if ((hourDiff >= 24 && price.getEndHour() == 24) || (hourDiff < price.getEndHour() && hourDiff >= price.getStartHour())){
          fee += price.getFee();
          hourDiff -= price.getEndHour();
          if (hourDiff < 0)
            break;
        }
      }
    }
    return fee;
  }

  @Override
  public Park parkingProcess(VehicleDto vehicleDto) throws ParkingLotException{
    Vehicle vehicle = vehicleRepository.findByLicensePlate(vehicleDto.getLicensePlate());
    if (vehicle == null){
      log.error("vehicle cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    Park park = parkRepository.findFirstByVehicleAndEndDateIsNullOrderByStartDate(vehicle);
    if (park == null){
      log.error("park cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    park.setEndDate(LocalDateTime.now());
    park.setFee(calculateFee(park));
    return park;
  }
}
