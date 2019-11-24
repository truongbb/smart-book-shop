package vn.ntqsolution.smart_shop.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.entity.RoleEntity;
import vn.ntqsolution.smart_shop.entity.UsersEntity;
import vn.ntqsolution.smart_shop.repository.user.UserRepositoryJpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepositoryJpa userRepositoryJpa;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder bcryptEncoder;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsersEntity user = userRepositoryJpa.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User " + username + " not found");
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    Set<RoleEntity> roleEntities = user.getRoleEntities();
    for (RoleEntity roleEntity : roleEntities) {
      grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getRoleName()));
    }
    return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }

  public UserDto loadUserDtoByUsername(String username) {
    Optional<UserDto> userDtoOptional = userService.findByUsernameOrEmailOrPhone("username", username);

    return userDtoOptional.map(userDto -> {
      String encodedPassword = bcryptEncoder.encode(userDto.getPassword());
      userDto.setPassword(encodedPassword);
      return userDto;
    }).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
  }

}
