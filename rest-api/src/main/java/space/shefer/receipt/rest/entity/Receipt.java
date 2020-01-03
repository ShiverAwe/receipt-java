package space.shefer.receipt.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Receipt {

  @Id
  @SequenceGenerator(name = "receipt_id_seq", sequenceName = "receipt_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_id_seq")
  @Column(name = "id", nullable = false)
  @Nullable
  private Long id;

  @Column(name = "date", nullable = false)
  private LocalDateTime date;

  @Column(name = "fn", nullable = false)
  private String fn;

  @Column(name = "fd", nullable = false)
  private String fd;

  @Column(name = "fp", nullable = false)
  private String fp;

  @Column(name = "sum", nullable = false)
  private Double sum;

  @Nullable
  @Column(name = "provider")
  private String provider;

  @Column(name = "status", nullable = false)
  private String status;

  @Nullable
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items;

}
