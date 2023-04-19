package com.iwlytteot.cookingBook.persistence;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IngredientWithCount {
    public IngredientWithCount() {
    }

    public IngredientWithCount(Ingredient ingredient, Integer count, Unit unit) {
        this.ingredient = ingredient;
        this.count = count;
        this.unit = unit;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ingredient ingredient;
    private Integer count;
    private Unit unit;
}
