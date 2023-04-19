package com.iwlytteot.cookingBook.repository;

import com.iwlytteot.cookingBook.persistence.IngredientWithCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientWithCountRepository extends JpaRepository<IngredientWithCount, Long> {
}