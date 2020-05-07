package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  @Id
  @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "amount", nullable = false)
  private Double amount;

  @ManyToOne(targetEntity = Receipt.class)
  @JoinColumn(name = "receipt_id")
  private Receipt receipt;

}
