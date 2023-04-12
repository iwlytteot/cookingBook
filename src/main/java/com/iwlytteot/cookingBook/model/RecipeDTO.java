package com.iwlytteot.cookingBook.model;

import com.iwlytteot.cookingBook.persistence.TimeComplexity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents user's input when creating new recipe.
 */
@Getter
@Setter
public class RecipeDTO extends RecipeBase{
    public RecipeDTO(String name, String description, Integer portion, TimeComplexity timeComplexity,
                     List<String> instructions) {
        super(name, description, portion, timeComplexity, instructions);
    }

    private List<IngredientWithCountDTO> ingredients;
}
