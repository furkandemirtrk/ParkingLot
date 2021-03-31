package com.huawei.parkinglot.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City{

  @Id
  private Long id;
  private String name;
}
