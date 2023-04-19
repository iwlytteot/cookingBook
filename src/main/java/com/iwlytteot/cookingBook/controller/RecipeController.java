package com.iwlytteot.cookingBook.controller;

import com.iwlytteot.cookingBook.exception.IngredientNotFoundException;
import com.iwlytteot.cookingBook.exception.RecipeNotFoundException;
import com.iwlytteot.cookingBook.model.RecipeDTO;
import com.iwlytteot.cookingBook.persistence.IngredientWithCount;
import com.iwlytteot.cookingBook.persistence.Recipe;
import com.iwlytteot.cookingBook.repository.IngredientRepository;
import com.iwlytteot.cookingBook.repository.IngredientWithCountRepository;
import com.iwlytteot.cookingBook.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientWithCountRepository ingredientWithCountRepository;
    private final CloudController cloudController;

    /**
     * Adds new recipe with ingredients
     * @param input User's input
     */
    @PostMapping("/recipe")
    public final Long addRecipe(@RequestBody RecipeDTO input) {
        var ingredients = new ArrayList<IngredientWithCount>();
        input.getIngredients().forEach(ingredient -> ingredients.add(ingredientWithCountRepository.save(ingredient)));
        var recipe = new Recipe(input.getName(), input.getDescription(), input.getPortion(), input.getTimeComplexity(),
                input.getInstructions(), ingredients);

        return recipeRepository.save(recipe).getId();
    }

    /**
     * Uploads new image of a recipe
     * @param id ID of recipe
     * @param image Multipart File as image
     * @throws IOException throws if there was a problem with uploading image
     */
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
    public final List<IngredientWithCount> getIngredients(@PathVariable Long id) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));

        return recipe.getIngredients();
    }

    /**
     * Adds or updates ingredient for a recipe
     * @param id of Recipe
     */
    @PutMapping("/recipe/{id}/ingredient")
    public final void upsertIngredient(@PathVariable Long id, @RequestBody IngredientWithCount input) {
        var recipe = recipeRepository.findById(id).orElseThrow(() ->
                new RecipeNotFoundException("Recipe with ID " + id + " has not been found."));
        ingredientRepository.findById(input.getIngredient().getId()).orElseThrow(() ->
                new IngredientNotFoundException("Ingredient with ID " + input.getIngredient().getId() + " has not been found."));
        recipe.getIngredients().add(input);

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
