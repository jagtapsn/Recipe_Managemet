package com.recipe.springbootrecipeapp.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequest {

	private String recipeName;
	private String recipeType;
	private Integer servings;
	private String instrutions;
	private int calories;
	private List<IngredientRequest> ingredients;

}
