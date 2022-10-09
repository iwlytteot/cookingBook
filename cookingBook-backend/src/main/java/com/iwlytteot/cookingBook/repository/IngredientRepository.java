package com.iwlytteot.cookingBook.repository;

import com.iwlytteot.cookingBook.persistence.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
