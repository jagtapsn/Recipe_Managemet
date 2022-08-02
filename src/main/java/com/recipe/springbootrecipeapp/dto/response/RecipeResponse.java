package com.recipe.springbootrecipeapp.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeResponse {

	private String receipeName;
	private String receipeType;
	private Integer servings;
	private String instrutions;
	private int calories;
	private List<IngredientResponse> ingredientResponses;

}
