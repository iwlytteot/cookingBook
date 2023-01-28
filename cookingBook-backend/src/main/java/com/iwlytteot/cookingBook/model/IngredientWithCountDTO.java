package com.iwlytteot.cookingBook.model;

import com.iwlytteot.cookingBook.persistence.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientWithCountDTO {
    private Ingredient ingredient;
    private Integer count;
}
