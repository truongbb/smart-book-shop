package vn.ntqsolution.smart_shop.repository.user;

import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.entity.UsersEntity;

import java.util.List;

public interface UserRepository {

  /**
   * @param searchField is one of "email", "username", "phone"
   * @param value       is search value for corresponding search field
   * @return List<UserDto>
   */
  List<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value);

  boolean updateUserEntity(UsersEntity usersEntity);

}
