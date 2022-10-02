package com.brokilone.tacooauthclient.configuration;

import com.brokilone.tacooauthclient.model.Ingredient;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Kseniia Ushakova
 */
public class RestIngredientService implements IngredientService {
  private RestTemplate restTemplate;

  public RestIngredientService(String accessToken) {
    this.restTemplate = new RestTemplate();

    if (accessToken != null) {
      restTemplate.getInterceptors()
          .add(getBearerTokenInterceptor(accessToken));
    }
  }


  @Override
  public Iterable<Ingredient> findAll() {
    return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
        "http://localhost:8080/api/ingredients",
        Ingredient[].class)));
  }

  @Override
  public Ingredient addIngredient(Ingredient ingredient) {
    return restTemplate.postForObject(
        "http://localhost:8080/api/ingredients",
        ingredient,
        Ingredient.class);
  }

  private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
    ClientHttpRequestInterceptor interceptor = (request, bytes, execution) -> {
      request.getHeaders().add("Authorization", "Bearer " + accessToken);
      return execution.execute(request, bytes);
    };
    return interceptor;
  }


}
