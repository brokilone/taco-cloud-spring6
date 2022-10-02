package brokilone.tacocloud.tacos.data;

import brokilone.tacocloud.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kseniia Ushakova
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
