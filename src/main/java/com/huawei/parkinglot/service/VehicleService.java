package com.huawei.parkinglot.service;

import com.huawei.parkinglot.exception.ParkingLotException;
import com.huawei.parkinglot.model.ParkDto;
import com.huawei.parkinglot.model.VehicleDto;


/**
 * Calculations will be placed on each vehicle type
 */
public interface VehicleService {

	/**
	 * Check Out Process
	 *
	 * @param vehicleDto
	 * @throws ParkingLotException
	 */
	ParkDto checkOut(VehicleDto vehicleDto)  throws ParkingLotException;


}
