package com.recipe.springbootrecipeapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.springbootrecipeapp.dao.IngredientServiceDao;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.model.Ingredients;
import com.recipe.springbootrecipeapp.repository.IngredientsRepository;

@Service
public class IngredientServiceDaoImpl implements IngredientServiceDao {

	@Autowired
	IngredientsRepository ingredientsRepository;

	@Override
	public List<IngredientResponse> getAllIngrdient() {
		List<Ingredients> ingredients = ingredientsRepository.findAllByOrderByName();

		return modelToDto(ingredients);
	}

	private List<IngredientResponse> modelToDto(List<Ingredients> ingredients) {
		List<IngredientResponse> ingredientResponses = new ArrayList<>();
		ingredients.forEach(ingredient -> {
			IngredientResponse ingredientResponse = new IngredientResponse();
			ingredientResponse.setId(ingredient.getId());
			ingredientResponse.setName(ingredient.getName());
			ingredientResponses.add(ingredientResponse);
		});

		return ingredientResponses;

	}

}
