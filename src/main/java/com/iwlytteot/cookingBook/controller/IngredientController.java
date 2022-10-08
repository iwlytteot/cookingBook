package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Adds new ingredient
     * @param input User's input
     */
    @PostMapping("/ingredient/add")
    public final void addIngredient(@RequestBody Ingredient input) {
        ingredientRepository.save(input);
    }
}
