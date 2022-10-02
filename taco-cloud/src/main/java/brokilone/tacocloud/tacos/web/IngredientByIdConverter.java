package brokilone.tacocloud.tacos.web;

import brokilone.tacocloud.tacos.Ingredient;
import brokilone.tacocloud.tacos.data.IngredientRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Kseniia Ushakova
 */

@Data
@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {
  private final IngredientRepository ingredientRepository;

  @Override
  public Ingredient convert(String id) {
    log.info("Find by id {}", id);
    return ingredientRepository.findById(id).orElse(null);
  }
}
