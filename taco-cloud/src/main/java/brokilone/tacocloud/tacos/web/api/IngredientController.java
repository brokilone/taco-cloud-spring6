package brokilone.tacocloud.tacos.web.api;

import brokilone.tacocloud.tacos.Ingredient;
import brokilone.tacocloud.tacos.data.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kseniia Ushakova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredients")
public class IngredientController {

  private final IngredientRepository repository;

  @GetMapping
  public Iterable<Ingredient> getAllIngredients(){
    return repository.findAll();
  }

  @PostMapping
//  @PreAuthorize("hasAuthority('SCOPE_writeIngredients')")
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
    return repository.save(ingredient);
  }

  @DeleteMapping("/{id}")
  //@PreAuthorize("hasAuthority('SCOPE_deleteIngredients')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteIngredient(@PathVariable(name = "id") String id) {
    repository.deleteById(id);
  }
}
