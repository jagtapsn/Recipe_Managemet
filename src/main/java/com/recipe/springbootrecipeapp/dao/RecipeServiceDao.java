package com.recipe.springbootrecipeapp.dao;

import java.util.List;

import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;

public interface RecipeServiceDao {
	List<RecipeResponse> getRecipeByFilters(RecipeFilterCriteria recipeFilterCriteria);

	List<RecipeResponse> getAllReceipes() throws RecordNotFoundException;

	RecipeResponse getRecipeByName(String name) throws RecordNotFoundException;

	RecipeResponse createRecipe(RecipeRequest recipeRequest);

	void deleteRecipeByName(String name) throws RecordNotFoundException;

	void deleteAllRecipe();

	RecipeResponse updateRecipeByName(String name, RecipeRequest recipeRequest) throws RecordNotFoundException;

}
