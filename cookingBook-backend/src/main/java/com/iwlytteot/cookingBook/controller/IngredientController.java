package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.model.IngredientUpdateDTO;
import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
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

    @PutMapping("ingredient/{id}")
    public final Ingredient updateIngredient(@PathVariable Long id, @RequestBody IngredientUpdateDTO input) {
        var ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Not found"));
        ingredient.setName(input.getName());
        ingredient.setWeight(input.getWeight());
        return ingredientRepository.save(ingredient);
    }

    /**
     * Deletes ingredient from all recipes and finally from repository
     * @param id of ingredient
     */
    @DeleteMapping(value = "ingredient/{id}")
    public final void deleteIngredient(@PathVariable Long id) {
        var ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Not found"));

        var recipes = recipeRepository.findAll();
        recipes.forEach(r -> r.getIngredients().remove(ingredient));
        ingredientRepository.delete(ingredient);
    }
}
