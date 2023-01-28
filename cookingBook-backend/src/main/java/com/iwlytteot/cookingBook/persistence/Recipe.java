package com.iwlytteot.cookingBook.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iwlytteot.cookingBook.model.RecipeBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents cooking recipe. Holds the data needed to cook food.
 * It is expected that a portion is for one person, otherwise 'portion' is set to different value.
 */
@Entity
@Getter
@Setter
public class Recipe extends RecipeBase {
    public Recipe() {}

    public Recipe(String name, String description, int portion, TimeComplexity timeComplexity) {
        super(name, description, portion, timeComplexity);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "recipe_and_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @Column(name = "count")
    @JsonIgnore
    private Map<Ingredient, Integer> ingredients = new HashMap<>();

    //private byte[] image; TODO
}
