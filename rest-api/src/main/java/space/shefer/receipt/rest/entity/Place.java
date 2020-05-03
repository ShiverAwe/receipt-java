package space.shefer.receipt.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "place2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Place extends BaseUuidIdEntity {

  @Column(name = "inn", nullable = false)
  private String inn;

  @Column(name = "simple_name")
  private String simpleName;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "address")
  private String address;

  @Column(name = "mcc")
  private String mcc;

}
