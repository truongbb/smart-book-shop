package vn.ntqsolution.smart_shop.repository.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ntqsolution.smart_shop.entity.GenderEntity;

@Repository
public interface GenderRepositoryJpa extends JpaRepository<GenderEntity, Long> {

  GenderEntity findOneByGenderValue(String genderValue);

}
