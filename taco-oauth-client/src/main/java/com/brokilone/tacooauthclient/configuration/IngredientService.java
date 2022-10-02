package com.brokilone.tacooauthclient.configuration;

import com.brokilone.tacooauthclient.model.Ingredient;

/**
 * @author Kseniia Ushakova
 */
public interface IngredientService {

  Iterable<Ingredient> findAll();

  Ingredient addIngredient(Ingredient ingredient);
}
