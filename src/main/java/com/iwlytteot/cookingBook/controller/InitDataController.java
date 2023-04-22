package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.persistence.*;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.IngredientWithCountRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class InitDataController {
    private final IngredientRepository ingredientRepository;
    private final IngredientWithCountRepository ingredientWithCountRepository;
    private final RecipeRepository recipeRepository;

    public InitDataController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,
                              IngredientWithCountRepository ingredientWithCountRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientWithCountRepository = ingredientWithCountRepository;
    }

    @PostMapping("/init")
    public final void init() {
        var ingredient = ingredientRepository.save(new Ingredient("Sugar"));
        var ingredient2 = ingredientRepository.save(new Ingredient("Egg"));

        var ingredientWithCount1 = ingredientWithCountRepository.save(new IngredientWithCount(ingredient, 15, Unit.g));
        var ingredientWithCount2 = ingredientWithCountRepository.save(new IngredientWithCount(ingredient2, 3, Unit.ks));


        var recipe = new Recipe("Pancake", "The best food", 2,
                new TimeComplexity("1h", "5m"),
                List.of("Prepare eggs and sugar", "Put it on skillet"), List.of(ingredientWithCount1), 5);
        recipeRepository.save(recipe);

        recipe = new Recipe("Bread with eggs", "The second best food", 1,
                new TimeComplexity("1h", "5m"), List.of("Add eggs to skillet", "Put eggs on bread"),
                List.of(ingredientWithCount2), 4);
        recipeRepository.save(recipe);
    }
}
