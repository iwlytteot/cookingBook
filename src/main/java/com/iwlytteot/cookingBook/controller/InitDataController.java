package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.persistence.Recipe;
import com.iwlytteot.cookingBook.persistence.TimeComplexity;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InitDataController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public InitDataController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @PostMapping("/init")
    public final void init() {
        var ingredient = ingredientRepository.save(new Ingredient("Sugar", 1));
        var ingredient2 = ingredientRepository.save(new Ingredient("Egg", 50));
        var ingredient3 = ingredientRepository.save(new Ingredient("Bread", 200));

        var recipe = new Recipe("Pancake", "The best food", 2,
                new TimeComplexity("1h", "5m"),
                List.of("Prepare eggs and sugar", "Put it on skillet"));
        var ingredients = recipe.getIngredients();
        ingredients.put(ingredient, 300);
        ingredients.put(ingredient2, 2);
        recipeRepository.save(recipe);

        recipe = new Recipe("Bread with eggs", "The second best food", 1,
                new TimeComplexity("1h", "5m"), List.of("Add eggs to skillet", "Put eggs on bread"));
        ingredients = recipe.getIngredients();
        ingredients.put(ingredient2, 2);
        ingredients.put(ingredient3, 1);
        recipeRepository.save(recipe);
    }
}
