package com.huawei.parkinglot.service.impl;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.enums.ErrorCodeEnum;
import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.model.ParkDto;
import com.huawei.parkinglot.model.VehicleDto;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.service.ParkService;
import com.huawei.parkinglot.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service("sedanVehicleService")
public class SedanVehicleServiceImpl implements VehicleService{

	private final ParkRepository parkRepository;
	private final ParkService parkService;
	private final ModelMapper modelMapper;

	@Override
	public ParkDto checkOut(VehicleDto vehicleDto)  throws ParkingLotException {
		try {
			Park park = parkService.parkingProcess(vehicleDto);
			park.setFee(((Sedan) park.getVehicle()).getMultiplier(park.getFee()));
			return modelMapper.map(parkRepository.save(park), ParkDto.class);
		} catch (ClassCastException e) {
			throw new ParkingLotException(ErrorCodeEnum.VEHICLE_TYPE_ERROR);
		}
	}

}
