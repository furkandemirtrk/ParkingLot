package com.huawei.parkinglot.service.impl;

import com.huawei.parkinglot.entity.City;
import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.Price;
import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.enums.ErrorCodeEnum;
import com.huawei.parkinglot.model.DailyIncomeOfTheParkingLotRequest;
import com.huawei.parkinglot.model.ParkingAreaDto;
import com.huawei.parkinglot.model.ParkingAreaFindByNameRequest;
import com.huawei.parkinglot.repository.CityRepository;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.service.ParkingAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingAreaServiceImpl implements ParkingAreaService{

  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkRepository parkRepository;
  private final CityRepository cityRepository;
  private final ModelMapper modelMapper;

  @Override
  public ParkingAreaDto save(ParkingAreaDto parkingAreaDto) throws ParkingLotException{
    log.info("save start");
    if (parkingAreaDto.getPriceList() == null || parkingAreaDto.getPriceList().isEmpty()){
      log.error("priceList is cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    if (calculateHours(parkingAreaDto) != 24){
      log.error("hours entries must be correct");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }

    return modelMapper.map(parkingAreaRepository.save(modelMapper.map(parkingAreaDto, ParkingArea.class)), ParkingAreaDto.class);
  }

  @Override
  public ParkingAreaDto update(Long id,ParkingAreaDto parkingAreaDto) throws ParkingLotException{
    log.info("update start");
    ParkingArea parkingArea = parkingAreaRepository.findById(id).orElse(null);
    if (parkingArea == null){
      log.error("parking area cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    if (calculateHours(parkingAreaDto) != 24){
      log.error("hours entries must be correct");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    City city = cityRepository.findById(parkingAreaDto.getCity().getId()).orElse(null);
    if (city == null){
      log.error("city  cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    parkingArea.setCapacity(parkingAreaDto.getCapacity());
    parkingArea.setCity(city);
    parkingArea.setName(parkingAreaDto.getName());
    parkingArea.getPriceList().clear();
    parkingArea.getPriceList().addAll(Arrays.asList(modelMapper.map(parkingAreaDto.getPriceList(), Price[].class)));
    parkingAreaRepository.save(parkingArea);

    return modelMapper.map(parkingAreaRepository.save(parkingArea), ParkingAreaDto.class);
  }

  @Override
  public Boolean delete(Long id) throws ParkingLotException{
    log.info("delete start");
    ParkingArea parkingArea = parkingAreaRepository.findById(id).orElse(null);
    if (parkingArea == null){
      log.error("parking area cannot be null");
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    if (parkRepository.countParkByEndDateIsNullAndParkingArea(parkingArea) != 0){
      log.error("There is  car in the parking area");
      throw new ParkingLotException(ErrorCodeEnum.THERE_IS_VEHICLE_IN_THE_PARKING_LOT);
    }
    List<Park> parkList = parkRepository.findAllByParkingArea(parkingArea);
    if (!parkList.isEmpty()){
      parkRepository.deleteAll(parkList);
    }
    parkingAreaRepository.deleteById(id);
    return true;
  }

  @Override
  public ParkingAreaDto findByName(ParkingAreaFindByNameRequest parkingAreaFindByNameRequest) throws ParkingLotException{
    log.info("findByName start");
    return modelMapper.map(parkingAreaRepository.findByName(parkingAreaFindByNameRequest.getName()), ParkingAreaDto.class);
  }

  @Override
  public Double dailyIncomeOfTheParkingLot(DailyIncomeOfTheParkingLotRequest dailyIncomeOfTheParkingLotRequest) throws ParkingLotException{
    log.info("dailyIncomeOfTheParkingLot start {} {},", dailyIncomeOfTheParkingLotRequest.getDate(), dailyIncomeOfTheParkingLotRequest.getParkingAreaId());
    ParkingArea parkingArea = parkingAreaRepository.findById(dailyIncomeOfTheParkingLotRequest.getParkingAreaId()).orElse(null);
    if (parkingArea == null){
      throw new ParkingLotException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    return parkingAreaRepository.dailyIncomeOfTheParkingLot(dailyIncomeOfTheParkingLotRequest.getDate(), dailyIncomeOfTheParkingLotRequest.getParkingAreaId());
  }

  private int calculateHours(ParkingAreaDto parkingAreaDto){
    return parkingAreaDto.getPriceList().stream().mapToInt(price -> price.getEndHour() - price.getStartHour()).sum();
  }
}
