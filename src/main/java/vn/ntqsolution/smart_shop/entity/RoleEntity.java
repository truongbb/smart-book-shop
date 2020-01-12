package vn.ntqsolution.smart_shop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ROLE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
  @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
  Long id;

  @Column(name = "ROLE_NAME", nullable = false)
  String roleName;

//  @ManyToMany(mappedBy = "roles")
//  Set<UsersEntity> users;
}
