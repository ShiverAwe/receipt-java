package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private ReceiptStatus status;

  @Nullable
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "place_id")
  private Place place;

  @Builder.Default
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items = new ArrayList<>();

  @Column(name = "merchant_name")
  private String merchantName;

  @Column(name = "merchant_inn")
  private String merchantInn;

  @Column(name = "merchant_place_address")
  private String merchantPlaceAddress;

}
