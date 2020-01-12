package vn.ntqsolution.smart_shop.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base repository.
 *
 * @author truongbb
 * @version 1.0
 * @since 2019-11-16
 */

@Repository
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseRepository {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  protected Session getSession() {
    return entityManager.unwrap(Session.class);
  }
}
