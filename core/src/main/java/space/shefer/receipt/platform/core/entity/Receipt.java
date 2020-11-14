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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt extends BaseUuidIdEntity {

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

  @Builder.Default
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items = new ArrayList<>();

  @Nullable
  @Column(name = "merchant_name")
  private String merchantName;

  @Nullable
  @Column(name = "merchant_inn")
  private String merchantInn;

  @Nullable
  @Column(name = "merchant_place_address")
  private String merchantPlaceAddress;

  @Nullable
  @ManyToOne
  @JoinColumn(name = "user_profile_id")
  private UserProfile userProfile;
  
  @Column(name = "load_attempts", nullable = false, columnDefinition = "INT DEFAULT 0")
  private long loadAttempts;

}
