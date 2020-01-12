package vn.ntqsolution.smart_shop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  String username;

  @Column(name = "PASSWORD", nullable = false)
  String password;

  @Column(name = "IS_ACTIVE", nullable = false)
  Boolean isActive;

  @Column(name = "AVATAR_URL")
  String avatarUrl;

  @Column(name = "ACTIVE_TOKEN")
  String activeToken;

  @OneToOne
  @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
  PersonEntity person;

  @ManyToMany
  @JoinTable(
    name = "USER_ROLE",
    joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
  )
  Set<RoleEntity> roleEntities;

}
