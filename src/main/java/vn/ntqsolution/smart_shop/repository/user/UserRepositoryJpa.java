package vn.ntqsolution.smart_shop.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ntqsolution.smart_shop.entity.UsersEntity;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UsersEntity, Long> {

  UsersEntity findByUsername(String username);

}
