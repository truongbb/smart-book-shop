package vn.ntqsolution.smart_shop.repository.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.repository.BaseRepository;
import vn.ntqsolution.smart_shop.utils.DataUtil;
import vn.ntqsolution.smart_shop.utils.SQLBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

  @Override
  public List<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value) {
    List<UserDto> userDtos = null;

    try {
      StringBuilder sql = new StringBuilder(SQLBuilder.getSqlFromFile(SQLBuilder.SQL_MODULE_USER, "find_by_username_mail_phone"));
      Map<String, Object> parameters = new HashMap<>();
      if ("email".equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
        sql.append(" and email = :p_email");
        parameters.put("p_email", value);
      } else if ("username".equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
        sql.append(" and username = :p_username");
        parameters.put("p_username", value);
      } else if ("phone".equalsIgnoreCase(searchField) && DataUtil.isNotNullAndEmptyString(value)) {
        sql.append(" and phone_number = :p_phone");
        parameters.put("p_phone", value);
      }
      userDtos = getNamedParameterJdbcTemplate().query(sql.toString(), parameters, new BeanPropertyRowMapper<>(UserDto.class));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return userDtos;
  }
}
