package com.huawei.parkinglot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "parking_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingArea {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Integer capacity;
  @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  private City city;
  @JoinColumn(name = "parking_area_id")
  @OneToMany(targetEntity = Price.class ,fetch = FetchType.EAGER, cascade= {CascadeType.ALL},  orphanRemoval=true)
  private List<Price> priceList;

}
