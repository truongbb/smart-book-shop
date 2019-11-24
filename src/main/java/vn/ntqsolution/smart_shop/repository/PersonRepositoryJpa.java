package vn.ntqsolution.smart_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.ntqsolution.smart_shop.entity.PersonEntity;

@Repository
public interface PersonRepositoryJpa extends JpaRepository<PersonEntity, Long> {
}
