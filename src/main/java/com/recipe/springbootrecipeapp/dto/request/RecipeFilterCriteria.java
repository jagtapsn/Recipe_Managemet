package com.recipe.springbootrecipeapp.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiParam;


public class RecipeFilterCriteria {
	@Valid    
	@ApiParam(name = "receipeType", value = "receipeType should veg or Non Veg", required = false, type = "string")
	private String receipeType;
	
	@ApiParam(name = "ingredients", value = "specific ingredient require", required = false, type = "string")
	private String ingredients;
	
	@ApiParam(name = "servings", value = "Servie to number of peoples", required = false, type = "integer",example = "0")
	@PositiveOrZero
    private Integer servings;
	
	@ApiParam(name = "instrutions", value = "Sepecific instrution", required = false, type = "string")
	private String instrutions;

	public String getReceipeType() {
		return receipeType;
	}

	public void setReceipeType(String receipeType) {
		this.receipeType = receipeType;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getInstrutions() {
		return instrutions;
	}

	public void setInstrutions(String instrutions) {
		this.instrutions = instrutions;
	}
	
	
		
}
