package com.huawei.parkinglot.util;

import com.huawei.parkinglot.entity.City;
import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.Price;
import com.huawei.parkinglot.entity.vehicle.Minivan;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.repository.CityRepository;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DBInitializerUtil{

  private final ParkingAreaRepository parkingAreaRepository;
  private final ParkRepository parkRepository;
  private final VehicleRepository vehicleRepository;
  private final CityRepository cityRepository;

  @PostConstruct
  private void initDb(){
    if (parkingAreaRepository.count() <= 0){
      createParkingArea();
    }
    if (parkRepository.count() <= 0){
      createPark();
    }

  }

  private void createParkingArea(){

    City city1= City.builder().id(1L).name("Adana").build();
    City city2= City.builder().id(6L).name("Ankara").build();
    City city3= City.builder().id(10L).name("Balıkesir").build();
    City city4= City.builder().id(34L).name("İstanbul").build();
    City city5= City.builder().id(35L).name("İzmir").build();

    cityRepository.saveAll(Arrays.asList(city1,city2,city3, city4,city5));

    List<Price> priceList1 = new ArrayList<>();
    priceList1.add(Price.builder().startHour(0).endHour(2).fee(10).build());
    priceList1.add(Price.builder().startHour(2).endHour(4).fee(12).build());
    priceList1.add(Price.builder().startHour(4).endHour(8).fee(15).build());
    priceList1.add(Price.builder().startHour(8).endHour(14).fee(17).build());
    priceList1.add(Price.builder().startHour(14).endHour(24).fee(20).build());

    List<Price> priceList2 = new ArrayList<>();
    priceList2.add(Price.builder().startHour(0).endHour(3).fee(10).build());
    priceList2.add(Price.builder().startHour(3).endHour(6).fee(12).build());
    priceList2.add(Price.builder().startHour(6).endHour(9).fee(15).build());
    priceList2.add(Price.builder().startHour(9).endHour(15).fee(17).build());
    priceList2.add(Price.builder().startHour(15).endHour(24).fee(20).build());


    List<ParkingArea> parkingAreaList = new ArrayList<>();
    parkingAreaList.add(ParkingArea.builder().name("Adana Otoparkı").city(city1).capacity(20).priceList(priceList1).build());
    parkingAreaList.add(ParkingArea.builder().name("Ankara Otoparkı").city(city2).capacity(50).priceList(priceList2).build());
    parkingAreaList.add(ParkingArea.builder().name("Balıkesir Otoparkı").city(city3).capacity(15).priceList(priceList2).build());
    parkingAreaList.add(ParkingArea.builder().name("İstanbul Otoparkı").city(city4).capacity(60).priceList(priceList2).build());
    parkingAreaList.add(ParkingArea.builder().name("İzmir Otoparkı").city(city5).capacity(40).priceList(priceList2).build());

    parkingAreaRepository.saveAll(parkingAreaList);

  }

  private void createPark(){
    Sedan sedan = new Sedan("34FD123");
    SUV suv = new SUV("1AA111");
    Minivan minivan = new Minivan("22BB222");

    vehicleRepository.saveAll(Arrays.asList(sedan,suv,minivan));

    Park park = Park.builder().vehicle(sedan).startDate(LocalDateTime.now()).parkingArea(parkingAreaRepository.findById(1L).orElse(null)).build();
    Park park2 = Park.builder().vehicle(suv).startDate(LocalDateTime.now()).parkingArea(parkingAreaRepository.findById(1L).orElse(null)).build();
    Park park3 = Park.builder().vehicle(minivan).startDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusHours(5)).fee(15D).parkingArea(parkingAreaRepository.findById(2L).orElse(null)).build();

    parkRepository.saveAll(Arrays.asList(park,park2,park3));
  }
}
