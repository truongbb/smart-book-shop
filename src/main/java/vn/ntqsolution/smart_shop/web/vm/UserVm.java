package vn.ntqsolution.smart_shop.web.vm;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVm {

  String username;
  String password;

  String firstName;
  String midName;
  String lastName;
  Boolean isActive;
  String avatarUrl;

  Date birthday;
  String address;
  String email;
  String phoneNumber;
  String gender;

  List<String> roles;


}
