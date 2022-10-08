package com.iwlytteot.cookingBook.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Ingredient {
    public Ingredient() {
    }

    public Ingredient(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Name of product
     */
    private String name;

    /**
     * Weight per unit
     */
    private int weight;
}
