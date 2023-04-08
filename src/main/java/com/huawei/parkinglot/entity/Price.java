package com.huawei.parkinglot.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "price")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer startHour;
  private Integer endHour;
  private Integer fee;

}
