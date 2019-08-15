package space.shefer.receipt.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;
  @Column(name = "date")
  private Date date;
  @Column(name = "fn")
  private String fn;
  @Column(name = "fd")
  private String fd;
  @Column(name = "fp")
  private String fp;
  @Column(name = "sum")
  private Double sum;
  @Column(name = "provider")
  private String provider;
  @Column(name = "status")
  private String status;
  @ManyToOne
  @JoinColumn(name = "place_id")
  private Place place;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items;
}
