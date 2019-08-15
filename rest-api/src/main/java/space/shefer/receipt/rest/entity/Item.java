package space.shefer.receipt.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;
  @Column(name = "text")
  private String text;
  @Column(name = "price")
  private Double price;
  @Column(name = "amount")
  private Double amount;
  @ManyToOne(targetEntity = Receipt.class)
  @JoinColumn(name = "receipt_id")
  private Receipt receipt;
}
