package space.shefer.receipt.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "place")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @Column(name = "text")
  private String text; // TODO Why text, not name?
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name ="place_id")
  private List<Receipt> receipts;
}
