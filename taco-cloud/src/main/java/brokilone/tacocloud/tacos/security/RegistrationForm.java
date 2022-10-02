package brokilone.tacocloud.tacos.security;

import brokilone.tacocloud.tacos.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Kseniia Ushakova
 */
@Data
public class RegistrationForm {
  private String username;
  private String password;
  private String fullname;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String phone;

  public User toUser(PasswordEncoder encoder) {
    return new User(username, encoder.encode(password),
        fullname, street, city, state, zip, phone);
  }
}
