package com.iwlytteot.cookingBook.persistence;

import com.iwlytteot.cookingBook.model.RecipeBase;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

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

    public Recipe(String name, String description, Integer portion, TimeComplexity timeComplexity, List<String> instructions,
                  List<IngredientWithCount> ingredients, Integer hamiMeter) {
        super(name, description, portion, timeComplexity, instructions, ingredients, hamiMeter);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * ID of image, that is saved on cloud
     */
    @Nullable
    private String imageId = "1_RF-4vMresQpylzTnrA2m9UIWmw4sTvX";
}
