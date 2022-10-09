package com.iwlytteot.cookingBook.model;

import com.iwlytteot.cookingBook.persistence.TimeComplexity;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Represents user's input when creating new recipe.
 */
@Getter
@Setter
public class RecipeInput extends RecipeBase{
    public RecipeInput(String name, String description, int portion, TimeComplexity timeComplexity) {
        super(name, description, portion, timeComplexity);
    }

    private Map<Long, Integer> ingredients;
}
