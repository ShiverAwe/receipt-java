package com.github.shiverawe.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@EqualsAndHashCode
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  @Getter
  @Setter
  private Long id;

  @Column(name = "text")
  @Getter
  @Setter
  private String text;

  @Column(name = "price")
  @Getter
  @Setter
  private Double price;

  @Column(name = "amount")
  @Getter
  @Setter
  private Double amount;

  @ManyToOne(targetEntity = Receipt.class)
  @JoinColumn(name = "receipt_id")
  @Getter
  @Setter
  private Receipt receipt;
}
