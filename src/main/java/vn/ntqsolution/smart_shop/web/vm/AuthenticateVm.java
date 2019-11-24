package vn.ntqsolution.smart_shop.web.vm;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticateVm implements Serializable {

  @NotNull
  String username;

  @NotNull
  String password;
}
