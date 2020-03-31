package space.shefer.receipt.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.shefer.receipt.rest.dto.ReceiptCreateDto;
import space.shefer.receipt.rest.dto.ReceiptMetaDto;
import space.shefer.receipt.rest.dto.ReceiptStatus;

import javax.annotation.Nullable;
import javax.persistence.*;
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

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "receipt_id")
  private List<Item> items = new ArrayList<>();


  public static ReceiptMetaDto toDto(Receipt receipt) {
    ReceiptMetaDto result = new ReceiptMetaDto();
    result.setId(receipt.getId());
    result.setFn(receipt.getFn());
    result.setFd(receipt.getFd());
    result.setFp(receipt.getFp());
    result.setDate(receipt.getDate());
    result.setSum(receipt.getSum());
    result.setProvider(receipt.getProvider());
    if (receipt.getPlace() != null) {
      result.setPlace(receipt.getPlace().getText());
    }
    result.setStatus(receipt.getStatus());
    return result;
  }

  public void setFrom(ReceiptMetaDto receipt){
    setDate(receipt.getDate());
    setFn(receipt.getFn());
    setFd(receipt.getFd());
    setFp(receipt.getFp());
    setSum(receipt.getSum());
    setStatus(receipt.getStatus());
    setProvider(receipt.getProvider());
    // TODO add place
  }

  public void setFrom(ReceiptCreateDto receipt){
    setDate(receipt.getDate());
    setFn(receipt.getFn());
    setFd(receipt.getFd());
    setFp(receipt.getFp());
    setSum(receipt.getSum());
  }

}
