package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Builder
@Table(name = "item")
@ToString
public class Item extends BaseUuidIdEntity {

  private String text;

  private Double price;

  private Double amount;

  @ManyToOne
  @JoinColumn(name = "receipt_id")
  private Receipt receipt;

}
