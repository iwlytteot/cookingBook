package com.iwlytteot.cookingBook.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class IngredientBase {
    /**
     * Name of product
     */
    private String name;
    /**
     * Weight per unit
     */
    private int weight;
}
