package brokilone.tacocloud.tacos;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kseniia Ushakova
 */
@Data
@RestResource(rel = "tacos", path = "tacos")
@Entity
public class Taco {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date createdAt = new Date();

  @NotNull
  @Size(min = 1, message = "Name must be at least 5 characters long")
  private String name;

  @NotNull
  @ManyToMany
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }
}
