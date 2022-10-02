package com.brokilone.tacooauthclient.controller;

import com.brokilone.tacooauthclient.configuration.IngredientService;
import com.brokilone.tacooauthclient.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kseniia Ushakova
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/ingredients")
public class IngredientsAdminController {

  private final IngredientService ingredientService;

  @GetMapping
  public String ingredients(Model model) {
    model.addAttribute("ingredients", ingredientService.findAll());
    return "ingredientsAdmin";
  }

  @PostMapping
  public String addIngredient(Ingredient ingredient) {
    ingredientService.addIngredient(ingredient);
    return "redirect:/admin/ingredients";
  }
}
