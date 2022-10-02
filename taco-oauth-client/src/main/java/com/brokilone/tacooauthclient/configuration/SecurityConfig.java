package com.brokilone.tacooauthclient.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Kseniia Ushakova
 */
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws
      Exception {
    http
        .authorizeRequests(
            authorizeRequests -> authorizeRequests.anyRequest().authenticated()
        )
        .oauth2Login(
            oauth2Login ->
                oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
        .oauth2Client(withDefaults());
    return http.build();
  }

  @Bean
  @RequestScope
  public IngredientService ingredientService(
      OAuth2AuthorizedClientService clientService) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    String accessToken = null;
    if (authentication.getClass()
        .isAssignableFrom(OAuth2AuthenticationToken.class)) {
      OAuth2AuthenticationToken oauthToken =
          (OAuth2AuthenticationToken) authentication;
      String clientRegistrationId =
          oauthToken.getAuthorizedClientRegistrationId();
      if (clientRegistrationId.equals("taco-admin-client")) {
        OAuth2AuthorizedClient client =
            clientService.loadAuthorizedClient(
                clientRegistrationId, oauthToken.getName());
        accessToken = client.getAccessToken().getTokenValue();
      }
    }
    return new RestIngredientService(accessToken);
  }
}
