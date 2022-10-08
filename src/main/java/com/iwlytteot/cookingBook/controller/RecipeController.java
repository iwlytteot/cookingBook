package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.model.RecipeInput;
import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.persistence.Recipe;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Adds new recipe with ingredients
     * @param input User's input
     */
    @PostMapping("/recipe")
    public final void addRecipe(@RequestBody RecipeInput input) {
        var recipe = new Recipe(input.getName(), input.getDescription(), input.getPortion(), input.getTimeComplexity());
        var ingredients = new HashMap<Ingredient, Integer>();

        input.getIngredients().forEach((k, v) -> ingredients.put(ingredientRepository.findById(k)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient with ID " + k + " has not been found!")), v));
        recipe.setIngredients(ingredients);

        recipeRepository.save(recipe);
    }

    /**
     * Gets all recipes sorted by newest
     * @return List of recipes
     */
    @GetMapping("/recipe")
    public final List<Recipe> getRecipesSortedByDate() {
        return recipeRepository.findAllSortNewest();
    }
}
