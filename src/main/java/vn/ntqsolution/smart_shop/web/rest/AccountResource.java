package vn.ntqsolution.smart_shop.web.rest;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ntqsolution.smart_shop.entity.UsersEntity;
import vn.ntqsolution.smart_shop.service.mail.MailService;
import vn.ntqsolution.smart_shop.service.user.UserService;
import vn.ntqsolution.smart_shop.web.vm.UserVm;

@RestController
@RequestMapping("${spring.data.rest.base-path}/account")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResource {

  @Autowired
  UserService userService;

  @Autowired
  MailService mailService;

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
            mailService.sendActivationEmail(user.getPerson().getEmail(), user.getUsername(), user.getActiveToken());
            return new ResponseEntity<>(HttpStatus.CREATED);
          })
        ));
  }

  @GetMapping(value = "/active-account/{username}/{activeToken}")
  public ResponseEntity<?> activeAccount(@PathVariable String username, @PathVariable String activeToken) {

    HttpHeaders textPlainHeaders = new HttpHeaders();
    textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

    return userService.findByUsernameOrEmailOrPhone("username", username)
      .map(user -> {
        if (!user.getActiveToken().equals(activeToken)) {
          return new ResponseEntity<>("Activation failed by incorrect activation token", textPlainHeaders, HttpStatus.BAD_REQUEST);
        }
        return userService.activeUser(user.getUsername()) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>("Activation failed", textPlainHeaders, HttpStatus.BAD_REQUEST);
      }).orElseGet(() -> new ResponseEntity<>("User" + username + " not found", textPlainHeaders, HttpStatus.BAD_REQUEST));
  }

}
