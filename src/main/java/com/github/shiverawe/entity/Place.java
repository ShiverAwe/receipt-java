package com.github.shiverawe.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "place")
@EqualsAndHashCode
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;
  @Column(name = "text")
  private String text;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="place_id")
  private Receipt receipts;

}
