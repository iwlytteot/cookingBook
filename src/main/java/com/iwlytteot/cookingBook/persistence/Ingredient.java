package com.iwlytteot.cookingBook.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Ingredient {
    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Name of product
     */
    private String name;
}
