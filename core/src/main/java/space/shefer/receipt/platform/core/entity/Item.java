package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseUuidIdEntity{

  private String text;

  private Double price;

  private Double amount;

  @ManyToOne
  @JoinColumn(name = "receipt_id")
  private Receipt receipt;

}
