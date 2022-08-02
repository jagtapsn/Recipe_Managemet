package com.recipe.springbootrecipeapp.service;

import java.util.List;

import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;

public interface RecipeService {

	List<RecipeResponse> getAllReceipes() throws RecordNotFoundException;

	RecipeResponse getRecipeByName(String name) throws RecordNotFoundException;

	RecipeResponse createRecipe(RecipeRequest recipeRequest);

	void deleteAllRecipe() throws RecordNotFoundException;

	RecipeResponse updateRecipe(String name, RecipeRequest recipeRequest) throws RecordNotFoundException;

	List<RecipeResponse> getRecipeDetailsByFilter(RecipeFilterCriteria recipeFilterCriteria)
			throws RecordNotFoundException;

	void deleteRecipeByName(String name) throws RecordNotFoundException;

}
