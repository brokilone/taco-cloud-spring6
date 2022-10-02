package brokilone.tacocloud.tacos.web;

import brokilone.tacocloud.tacos.Ingredient;
import brokilone.tacocloud.tacos.Ingredient.Type;
import brokilone.tacocloud.tacos.Taco;
import brokilone.tacocloud.tacos.TacoOrder;
import brokilone.tacocloud.tacos.data.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;

/**
 * @author Kseniia Ushakova
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    var ingredients = ingredientRepository.findAll();

    Type[] types = Type.values();

    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
    if (errors.hasErrors()) {
      return "design";
    }
    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);
    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
    return StreamSupport.stream(ingredients.spliterator(), false)
        .filter(ingredient -> ingredient.getType().equals(type))
        .collect(Collectors.toList());
  }
}
