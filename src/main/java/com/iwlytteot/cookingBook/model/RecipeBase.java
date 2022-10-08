package com.iwlytteot.cookingBook.model;

import com.iwlytteot.cookingBook.persistence.TimeComplexity;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class RecipeBase {
    /**
     * Name of recipe
     */
    private String name;
    /**
     * Description on how to cook it
     */
    @Lob
    private String description;
    private int portion = 1;
    @Embedded
    private TimeComplexity timeComplexity;
}
