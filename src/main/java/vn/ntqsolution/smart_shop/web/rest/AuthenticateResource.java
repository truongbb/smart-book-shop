package vn.ntqsolution.smart_shop.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ntqsolution.smart_shop.dto.UserDto;
import vn.ntqsolution.smart_shop.security.JwtTokenUtil;
import vn.ntqsolution.smart_shop.service.user.UserDetailsServiceImpl;
import vn.ntqsolution.smart_shop.service.user.UserService;
import vn.ntqsolution.smart_shop.web.vm.AuthenticateVm;
import vn.ntqsolution.smart_shop.web.vm.UserVm;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${spring.data.rest.base-path}/authenticate")
@Slf4j
public class AuthenticateResource {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticateVm authenticateVm) throws Exception {
    authenticate(authenticateVm.getUsername(), authenticateVm.getPassword());
    log.debug("AUTO AUTHENTICATE OK, find user.");
    final UserDto userDto = userDetailsServiceImpl.loadUserDtoByUsername(authenticateVm.getUsername());
    final String jwtToken = jwtTokenUtil.generateToken(userDto);
    Map<String, Object> tokenMap = new HashMap<>();
    tokenMap.put("token", jwtToken);
    tokenMap.put("user", userDto);
    return new ResponseEntity<>(tokenMap, HttpStatus.OK);
  }

  @PostMapping(value = "/resetPassword")
  public ResponseEntity<Boolean> resetPassword(@Valid @RequestBody UserVm userVm) {
    try {
      boolean result = userService.resetPassword(userVm.getEmail());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("Authenticate FAILED cause by user's disabled", e);
    } catch (BadCredentialsException ex) {
      throw new Exception("Authenticate FAILED cause by invalid credentials", ex);
    }
  }

}
