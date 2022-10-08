package com.iwlytteot.cookingBook.persistence;

import com.iwlytteot.cookingBook.model.IngredientBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Ingredient extends IngredientBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
