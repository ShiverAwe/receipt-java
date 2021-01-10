package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import space.shefer.receipt.platform.core.dto.ReceiptStatus;

import javax.annotation.Nullable;
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

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "receipt")
@ToString
public class Receipt extends BaseUuidIdEntity {

  private LocalDateTime date;

  private String fn;

  private String fd;

  private String fp;

  private Double sum;

  @Nullable
  private String provider;

  @Enumerated(EnumType.STRING)
  private ReceiptStatus status;

  @Builder.Default
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items = new ArrayList<>();

  @Nullable
  private String merchantName;

  @Nullable
  private String merchantInn;

  @Nullable
  private String merchantPlaceAddress;

  @Nullable
  @ManyToOne
  @JoinColumn(name = "user_profile_id")
  private UserProfile userProfile;

  private long loadAttempts;

}
