package vn.ntqsolution.smart_shop.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ntqsolution.smart_shop.entity.RoleEntity;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByRoleName(String roleName);
}
