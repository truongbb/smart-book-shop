package vn.ntqsolution.smart_shop.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.ntqsolution.smart_shop.entity.RoleEntity;

public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByRoleName(String roleName);
}
