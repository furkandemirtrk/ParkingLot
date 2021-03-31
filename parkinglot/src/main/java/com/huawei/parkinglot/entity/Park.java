package com.huawei.parkinglot.entity;

import com.huawei.parkinglot.entity.vehicle.Vehicle;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "park")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Park{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime startDate;
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime endDate;
  @ManyToOne
  @JoinColumn(name = "VEHICLE_ID")
  private Vehicle vehicle;
  @ManyToOne
  @JoinColumn(name = "PARKING_AREA_ID")
  private ParkingArea parkingArea;
  private Double fee;

}
