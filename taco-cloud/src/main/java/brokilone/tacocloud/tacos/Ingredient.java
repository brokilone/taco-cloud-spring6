package brokilone.tacocloud.tacos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Kseniia Ushakova
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient{
  @Id
  private String id;
  private String name;
  private Type type;


  public enum Type {
    WRAP,
    PROTEIN,
    VEGGIES,
    CHEESE,
    SAUCE
  }
}
