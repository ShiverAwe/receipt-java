package com.github.shiverawe.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipt")
@EqualsAndHashCode
public class Receipt {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  @Getter
  @Setter
  private Long id;

  @Column(name = "date")
  @Getter
  @Setter
  private Date date;

  @Column(name = "fn")
  @Getter
  @Setter
  private String fn;

  @Column(name = "fd")
  @Getter
  @Setter
  private String fd;

  @Column(name = "fp")
  @Getter
  @Setter
  private String fp;

  @Column(name = "sum")
  @Getter
  @Setter
  private Double sum;

  @Column(name = "provider")
  @Getter
  @Setter
  private String provider;

  @Column(name = "status")
  @Getter
  @Setter
  private String status;

  @ManyToOne
  @JoinColumn(name = "place_id")
  @Getter
  @Setter
  private Place place;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  @Getter
  @Setter
  private List<Item> receipts;

}
