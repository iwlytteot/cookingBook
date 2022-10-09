package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Adds new ingredient
     * @param input User's input
     */
    @PostMapping("/ingredient")
    public final void addIngredient(@RequestBody Ingredient input) {
        ingredientRepository.save(input);
    }

    @GetMapping("/ingredient/{id}")
    public final Ingredient getIngredient(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient with ID " + id + " has not been found."));
    }
    @GetMapping("/ingredient")
    public final List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }
}
