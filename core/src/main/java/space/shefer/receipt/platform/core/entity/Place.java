package space.shefer.receipt.platform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "place")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {

  @Id
  @SequenceGenerator(name="place_id_seq",sequenceName="place_id_seq", allocationSize=1)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="place_id_seq")
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "text", nullable = false)
  private String text; // TODO Why text, not name?

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name ="place_id")
  private List<Receipt> receipts;

}
