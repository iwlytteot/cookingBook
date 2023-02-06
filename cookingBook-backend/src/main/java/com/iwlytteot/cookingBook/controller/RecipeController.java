package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.exception.RecipeNotFoundException;
import com.iwlytteot.cookingBook.model.IngredientWithCountDTO;
import com.iwlytteot.cookingBook.model.RecipeDTO;
import com.iwlytteot.cookingBook.persistence.Ingredient;
import com.iwlytteot.cookingBook.persistence.Recipe;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final CloudController cloudController;

    /**
     * Adds new recipe with ingredients
     * @param input User's input
     */
    @PostMapping("/recipe")
    public final void addRecipe(@RequestBody RecipeDTO input) {
        var recipe = new Recipe(input.getName(), input.getDescription(), input.getPortion(), input.getTimeComplexity());
        var ingredients = new HashMap<Ingredient, Integer>();

        input.getIngredients().forEach((k, v) -> ingredients.put(ingredientRepository.findById(k)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient with ID " + k + " has not been found!")), v));
        recipe.setIngredients(ingredients);

        recipeRepository.save(recipe);
    }

    @PostMapping("/recipe/{id}/image")
    public final void addImage(@PathVariable Long id, @RequestParam("img") MultipartFile image) throws IOException {
        var imageId = cloudController.uploadFile(image);
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));
        recipe.setImageId(imageId);
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

    /**
     * Gets ingredients for a recipe
     * @param id of Recipe
     * @return list of ingredients with quantity
     */
    @GetMapping("/recipe/{id}/ingredient")
    public final List<IngredientWithCountDTO> getIngredients(@PathVariable Long id) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));

        var result = new ArrayList<IngredientWithCountDTO>();
        recipe.getIngredients().forEach((k, v) -> result.add(new IngredientWithCountDTO(k, v)));
        return result;
    }

    /**
     * Adds or updates ingredient for a recipe
     * @param id of Recipe
     */
    @PutMapping("/recipe/{id}/ingredient")
    public final void upsertIngredient(@PathVariable Long id, @RequestBody IngredientWithCountDTO input) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));
        var ingredient = ingredientRepository.findById(input.getIngredient().getId()).orElseThrow(() ->
                new IngredientNotFoundException("Ingredient with ID " + input.getIngredient().getId() + " has not been found."));
        recipe.getIngredients().put(ingredient, input.getCount());

        recipeRepository.save(recipe);
    }

    /**
     * Deletes recipe
     * @param id of Recipe
     */
    @DeleteMapping("/recipe/{id}")
    public final void deleteRecipe(@PathVariable Long id) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));

        recipeRepository.delete(recipe);
    }
}
