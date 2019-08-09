package com.github.shiverawe.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "merchant")
@EqualsAndHashCode
public class Merchant {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  @Getter
  @Setter
  private Long id;

  @Column(name = "inn")
  @Getter
  @Setter
  private String inn;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "last_update_time")
  @Getter
  @Setter
  private Date lastUpdateTime;

  @Column(name = "address")
  @Getter
  @Setter
  private String address;
}
