package vn.ntqsolution.smart_shop.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ntqsolution.smart_shop.entity.UsersEntity;
import vn.ntqsolution.smart_shop.service.user.UserService;
import vn.ntqsolution.smart_shop.web.vm.UserVm;

@RestController
@RequestMapping("${spring.data.rest.base-path}/account")
@Slf4j
public class AccountResource {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/register")
  public ResponseEntity<?> register(@RequestBody UserVm userVm) {

    HttpHeaders textPlainHeaders = new HttpHeaders();
    textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

    return userService.findByUsernameOrEmailOrPhone("email", userVm.getEmail())
      .map(user -> new ResponseEntity("Email already use", textPlainHeaders, HttpStatus.BAD_REQUEST))
      .orElseGet(() -> userService.findByUsernameOrEmailOrPhone("username", userVm.getUsername())
        .map(user -> new ResponseEntity("Username already use", textPlainHeaders, HttpStatus.BAD_REQUEST))
        .orElseGet(() -> userService.findByUsernameOrEmailOrPhone("phone", userVm.getPhoneNumber())
          .map(user -> new ResponseEntity("Phone number already use", textPlainHeaders, HttpStatus.BAD_REQUEST))
          .orElseGet(() -> {
            UsersEntity user = userService.createUser(userVm);

            log.debug("Created user: " + user);
            // send mail
            return new ResponseEntity<>(HttpStatus.CREATED);
          })
        ));
  }

}
