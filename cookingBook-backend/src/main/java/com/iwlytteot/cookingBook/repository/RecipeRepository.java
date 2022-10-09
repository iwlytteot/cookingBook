package com.iwlytteot.cookingBook.repository;

import com.iwlytteot.cookingBook.persistence.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT recipe from Recipe recipe ORDER BY recipe.createTime DESC")
    List<Recipe> findAllSortNewest();
}
