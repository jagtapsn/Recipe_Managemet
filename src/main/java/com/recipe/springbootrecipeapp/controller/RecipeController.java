package com.recipe.springbootrecipeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.service.IngredientService;
import com.recipe.springbootrecipeapp.service.RecipeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	IngredientService ingredientService;

	@ApiOperation(value = "Returns ingredients in alphabetical order", httpMethod = "GET", notes = "Returns ingredients in alphabetical order")
	@ApiResponses(value = { @ApiResponse(code = 200, response = RecipeResponse.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/ingredients")
	public List<IngredientResponse> getIngrendientsOrderByName() throws RecordNotFoundException {
		return ingredientService.getAllIngredient();
	}

	@ApiOperation(value = "Get recipe by receipeType ,ingredients ,servings,instrutions ", httpMethod = "GET", notes = "Get Recipe details by filter")
	@ApiResponses(value = { @ApiResponse(code = 200, response = RecipeResponse.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/recipesfilters")
	public List<RecipeResponse> getRecipeByFilter(
			@Validated RecipeFilterCriteria recipeFilterCriteria) throws RecordNotFoundException {
		return recipeService.getRecipeDetailsByFilter(recipeFilterCriteria);
		

	}

	@ApiOperation(value = "Get All recipe ", httpMethod = "GET", notes = "Get All Recipe ")
	@ApiResponses(value = { @ApiResponse(code = 200, response = RecipeResponse.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/recipes")
	public List<RecipeResponse> getAllRecipes() throws RecordNotFoundException {

		return recipeService.getAllReceipes();
	}

	@ApiOperation(value = "Get recipe by Name ", httpMethod = "GET", notes = "Get Recipe by Name ")
	@ApiResponses(value = { @ApiResponse(code = 200, response = RecipeResponse.class, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/recipes/{name}")
	public RecipeResponse getRecipeByName(@PathVariable(value = "name") String name) throws RecordNotFoundException {
		return recipeService.getRecipeByName(name);
		
	}

	@ApiOperation(value = "Create Recipe", httpMethod = "POST", notes = "Create Recipe")
	@ApiResponses(value = { @ApiResponse(code = 200, response = RecipeRequest.class, message = "OK") })
	@PostMapping(value = "/recipes/create")
	public RecipeResponse postRecipe(@RequestBody RecipeRequest recipeRequest) {
		return recipeService.createRecipe(recipeRequest);
	}

	@ApiOperation(value = "Delete Recipe by name ", httpMethod = "DELETE", notes = "Delete Recipe by name")
	@DeleteMapping("/recipes/{name}")
	public ResponseEntity<String> deleteRecipe(@PathVariable("name") String name) throws RecordNotFoundException {
		recipeService.deleteRecipeByName(name);
		return new ResponseEntity<>("Recipe has been deleted!", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete All Recipe", httpMethod = "DELETE", notes = "Delete All Recipe")
	@DeleteMapping("/recipes/delete")
	public ResponseEntity<String> deleteAllRecipes() throws RecordNotFoundException  {
		recipeService.deleteAllRecipe();
		return new ResponseEntity<>("All recipes have been deleted!", HttpStatus.OK);
	}

	@ApiOperation(value = "Update Recipe by name", httpMethod = "PUT", notes = "Update recipe by name")
	@PutMapping("/recipes/{name}")
	public RecipeResponse updateRecipe(@PathVariable("name") String name,
			@RequestBody RecipeRequest recipeRequest) throws RecordNotFoundException {
		return recipeService.updateRecipe(name, recipeRequest);
	}
}
