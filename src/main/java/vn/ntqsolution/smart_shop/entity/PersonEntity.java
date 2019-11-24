package vn.ntqsolution.smart_shop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "PERSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
  @SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_SEQ", allocationSize = 1)
  Long id;

  @Column(name = "FIRST_NAME", nullable = false)
  String firstName;

  @Column(name = "MID_NAME")
  String midName;

  @Column(name = "LAST_NAME", nullable = false)
  String lastName;

  @Column(name = "BIRTHDAY", nullable = false)
  Date birthday;

  @Column(name = "ADDRESS")
  String address;

  @Column(name = "EMAIL", nullable = false)
  String email;

  @Column(name = "PHONE_NUMBER", nullable = false)
  String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "GENDER_ID")
  GenderEntity genderEntity;


}
