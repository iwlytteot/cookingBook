package com.iwlytteot.cookingBook.repository;

import com.iwlytteot.cookingBook.persistence.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
