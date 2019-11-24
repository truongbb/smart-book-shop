package vn.ntqsolution.smart_shop.service.user;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.entity.GenderEntity;
import vn.ntqsolution.smart_shop.entity.PersonEntity;
import vn.ntqsolution.smart_shop.entity.RoleEntity;
import vn.ntqsolution.smart_shop.entity.UsersEntity;
import vn.ntqsolution.smart_shop.repository.PersonRepositoryJpa;
import vn.ntqsolution.smart_shop.repository.gender.GenderRepositoryJpa;
import vn.ntqsolution.smart_shop.repository.role.RoleRepositoryJpa;
import vn.ntqsolution.smart_shop.repository.user.UserRepository;
import vn.ntqsolution.smart_shop.repository.user.UserRepositoryJpa;
import vn.ntqsolution.smart_shop.utils.DataUtil;
import vn.ntqsolution.smart_shop.web.vm.UserVm;

import java.util.*;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  GenderRepositoryJpa genderRepositoryJpa;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  RoleRepositoryJpa roleRepositoryJpa;

  @Autowired
  UserRepositoryJpa userRepositoryJpa;

  @Autowired
  PersonRepositoryJpa personRepositoryJpa;

  @Override
  public Optional<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value) {
    List<UserDto> userDtos = userRepository.findByUsernameOrEmailOrPhone(searchField, value);

    if (userDtos == null) {
      return Optional.empty();
    }

    if (userDtos.size() > 0) {
      List<String> roles = new ArrayList<>();
      UserDto userDto = userDtos.get(0);
      userDtos.forEach(user -> {
        roles.add(user.getRoleName());
      });
      userDto.setRoles(roles);
      return Optional.of(userDto);
    } else {
      return Optional.empty();
    }
  }

  @Override
  @Transactional
  public UsersEntity createUser(UserVm userVm) {

    PersonEntity personEntity = new PersonEntity();

    GenderEntity gender = genderRepositoryJpa.findOneByGenderValue(userVm.getGender());
    personEntity.setGenderEntity(gender);

    personEntity.setFirstName(userVm.getFirstName());
    personEntity.setMidName(userVm.getMidName());
    personEntity.setLastName(userVm.getLastName());
    personEntity.setBirthday(userVm.getBirthday());
    personEntity.setAddress(userVm.getAddress());
    personEntity.setEmail(userVm.getEmail());
    personEntity.setPhoneNumber(userVm.getPhoneNumber());


    PersonEntity person = personRepositoryJpa.save(personEntity);


    UsersEntity usersEntity = new UsersEntity();
    usersEntity.setPerson(person);
    usersEntity.setUsername(userVm.getUsername());

    String encryptedPassword = passwordEncoder.encode(userVm.getPassword());
    usersEntity.setPassword(encryptedPassword);

    usersEntity.setAvatarUrl(userVm.getAvatarUrl());
    usersEntity.setIsActive(false);
    usersEntity.setActiveToken(DataUtil.generateRandomString());

    Set<RoleEntity> roles = new HashSet<>();
    userVm.getRoles().forEach(role -> {
      roles.add(roleRepositoryJpa.findByRoleName(role));
    });
    usersEntity.setRoleEntities(roles);

    UsersEntity user = userRepositoryJpa.save(usersEntity);
    return user;
  }

  @Override
  @Transactional
  public boolean activeUser(String username) {
    UsersEntity entity = userRepositoryJpa.findByUsername(username);
    entity.setIsActive(true);
    UsersEntity usersEntity = userRepositoryJpa.save(entity);
    return usersEntity != null;
  }
}
