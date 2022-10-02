package com.brokilone.tacooauthclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kseniia Ushakova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
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
