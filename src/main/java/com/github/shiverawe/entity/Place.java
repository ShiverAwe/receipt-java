package com.github.shiverawe.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "place")
@Data
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;
  @Column(name = "text")
  private String text; // TODO Why test, not name?
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="place_id")
  private Receipt receipts;
}
