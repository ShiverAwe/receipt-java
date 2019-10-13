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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  @Nullable
  private Long id;
  @Column(name = "date")
  private LocalDateTime date;
  @Column(name = "fn")
  private String fn;
  @Column(name = "fd")
  private String fd;
  @Column(name = "fp")
  private String fp;
  @Column(name = "sum")
  private Double sum;
  @Nullable
  @Column(name = "provider")
  private String provider;
  @Column(name = "status")
  private String status;
  @Nullable
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items;
}
