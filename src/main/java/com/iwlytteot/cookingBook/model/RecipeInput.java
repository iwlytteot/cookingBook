package com.iwlytteot.cookingBook.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Represents user's input when creating new recipe.
 */
@Getter
@Setter
public class RecipeInput extends RecipeBase{
    private Map<Long, Integer> ingredients;
}
