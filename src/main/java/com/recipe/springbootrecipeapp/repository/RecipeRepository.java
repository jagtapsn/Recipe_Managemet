package com.recipe.springbootrecipeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.springbootrecipeapp.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	Recipe findByName(String name);

	List<Recipe> findByIngredientsId(long id);

}