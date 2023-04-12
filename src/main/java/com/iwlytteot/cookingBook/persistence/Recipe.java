package com.iwlytteot.cookingBook.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iwlytteot.cookingBook.model.RecipeBase;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents cooking recipe. Holds the data needed to cook food.
 * It is expected that a portion is for one person, otherwise 'portion' is set to different value.
 */
@Entity
@Getter
@Setter
public class Recipe extends RecipeBase {
    public Recipe() {
    }

    public Recipe(String name, String description, Integer portion, TimeComplexity timeComplexity, List<String> instructions) {
        super(name, description, portion, timeComplexity, instructions);
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

    /**
     * ID of image, that is saved on cloud
     */
    @Nullable
    private String imageId = "1_RF-4vMresQpylzTnrA2m9UIWmw4sTvX";
}
