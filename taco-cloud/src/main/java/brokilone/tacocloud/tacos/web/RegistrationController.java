package brokilone.tacocloud.tacos.web;

import brokilone.tacocloud.tacos.data.UserRepository;
import brokilone.tacocloud.tacos.security.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kseniia Ushakova
 */
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @GetMapping
  public String registerForm(){
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepository.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
