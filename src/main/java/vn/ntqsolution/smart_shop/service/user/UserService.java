package vn.ntqsolution.smart_shop.service.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.entity.UsersEntity;
import vn.ntqsolution.smart_shop.web.vm.UserVm;

import java.util.Optional;

public interface UserService {

  /**
   * @param searchField is one of "email", "username", "phone"
   * @param value       is search value for corresponding search field
   * @return Optional<UserDto>
   */
  Optional<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value);

  UsersEntity createUser(UserVm userVm);

  boolean activeUser(String username);

  boolean resetPassword(String email) throws UsernameNotFoundException;


}
