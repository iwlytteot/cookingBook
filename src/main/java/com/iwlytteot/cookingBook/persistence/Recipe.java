package com.iwlytteot.cookingBook.persistence;

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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    //private User author;

    private String description;

    @Embedded
    private TimeComplexity timeComplexity;

    private int portion = 1;

    @ElementCollection
    @CollectionTable(name = "recipe_and_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    @MapKeyJoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @Column(name = "count")
    private Map<Ingredient, Integer> ingredients = new HashMap<>();

    public void addIngredient(Ingredient ingredient, Integer count) {
        ingredients.put(ingredient, count);
    }

    //private byte[] image; TODO
}
