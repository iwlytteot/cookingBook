package com.iwlytteot.cookingBook.model;

import com.iwlytteot.cookingBook.persistence.IngredientWithCount;
import com.iwlytteot.cookingBook.persistence.TimeComplexity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@MappedSuperclass
@Data
@AllArgsConstructor
public class RecipeBase {
    public RecipeBase() {}

    /**
     * Name of recipe
     */
    private String name;
    /**
     * Description on how to cook it
     */
    @Lob
    private String description;
    private Integer portion = 1;
    @Embedded
    private TimeComplexity timeComplexity;
    private final Instant createTime = Instant.now();
    /**
     * Set of instructions on how to proceed with recipe
     */
    @ElementCollection
    private List<String> instructions;
    @OneToMany
    private List<IngredientWithCount> ingredients;

}
