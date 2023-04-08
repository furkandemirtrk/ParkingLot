package com.huawei.parkinglot.entity.vehicle;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_COLUMN",
		discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Vehicle {

	@Id
	private String licensePlate;

	public static final Double MULTIPLIER = 0D;

	public Double getMultiplier(Double fee){
		return fee * (fee * MULTIPLIER);
	}

}
