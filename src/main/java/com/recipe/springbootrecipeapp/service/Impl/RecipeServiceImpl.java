package com.recipe.springbootrecipeapp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.springbootrecipeapp.dao.RecipeServiceDao;
import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeServiceDao recipeServiceDao;

	@Override
	public List<RecipeResponse> getAllReceipes() throws RecordNotFoundException {
		return recipeServiceDao.getAllReceipes();

	}

	@Override
	public RecipeResponse getRecipeByName(String name) throws RecordNotFoundException {
		return recipeServiceDao.getRecipeByName(name);

	}

	@Override
	public RecipeResponse createRecipe(RecipeRequest recipeRequest) {
		return recipeServiceDao.createRecipe(recipeRequest);

	}

	@Override
	public void deleteRecipeByName(String name) throws RecordNotFoundException {
		recipeServiceDao.deleteRecipeByName(name);

	}

	@Override
	public void deleteAllRecipe() {
		recipeServiceDao.deleteAllRecipe();

	}

	@Override
	public RecipeResponse updateRecipe(String name, RecipeRequest recipeRequest) throws RecordNotFoundException {

		return recipeServiceDao.updateRecipeByName(name, recipeRequest);

	}

	@Override
	public List<RecipeResponse> getRecipeDetailsByFilter(RecipeFilterCriteria recipeFilterCriteria)
			throws RecordNotFoundException {
		List<RecipeResponse> recipeResponseList = recipeServiceDao.getRecipeByFilters(recipeFilterCriteria);
		if (!recipeResponseList.isEmpty()) {
			return recipeResponseList;
		}
		throw new RecordNotFoundException("Record Not found");
	}

}
