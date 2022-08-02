package com.recipe.springbootrecipeapp.service;

import java.util.List;

import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;

public interface IngredientService {

	List<IngredientResponse> getAllIngredient() throws RecordNotFoundException;

}
