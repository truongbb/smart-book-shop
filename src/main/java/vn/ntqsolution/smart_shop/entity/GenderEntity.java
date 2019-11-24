package vn.ntqsolution.smart_shop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GENDER")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenderEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENDER_SEQ")
  @SequenceGenerator(name = "GENDER_SEQ", sequenceName = "GENDER_SEQ", allocationSize = 1)
  Long id;

  @Column(name = "GENDER_VALUE", nullable = false)
  String genderValue;
}
