package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.exception.RecipeNotFoundException;
import com.iwlytteot.cookingBook.model.IngredientWithCount;
import com.iwlytteot.cookingBook.model.RecipeInput;
import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.persistence.Recipe;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping(
            value = "/recipe")
    public final List<Recipe> getRecipesSortedByDate() {
        return recipeRepository.findAllSortNewest();
    }

    @GetMapping(value = "/recipe/{id}/ingredients")
    public final List<IngredientWithCount> getIngredients(@PathVariable Long id) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));

        var result = new ArrayList<IngredientWithCount>();
        recipe.getIngredients().forEach((k, v) -> result.add(new IngredientWithCount(k, v)));
        return result;
    }
}
