package com.recipe.springbootrecipeapp.dao;

import java.util.List;

import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;

public interface IngredientServiceDao {
	List<IngredientResponse> getAllIngrdient();
}
