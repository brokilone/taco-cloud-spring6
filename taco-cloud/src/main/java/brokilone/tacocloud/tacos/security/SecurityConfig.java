package brokilone.tacocloud.tacos.security;

import brokilone.tacocloud.tacos.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Kseniia Ushakova
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      var user = userRepository.findByUsername(username);
      if (user != null) return user;
      throw new UsernameNotFoundException("User '" + username + "' not found");
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeRequests()
        .antMatchers("/design", "/orders").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/api/ingredients")
        .hasAuthority("SCOPE_writeIngredients")
        .antMatchers(HttpMethod.DELETE, "/api/ingredients")
        .hasAuthority("SCOPE_deleteIngredients")
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/design")
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .build();
  }
}
