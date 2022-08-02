package com.recipe.springbootrecipeapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.springbootrecipeapp.dao.RecipeServiceDao;
import com.recipe.springbootrecipeapp.dto.request.IngredientRequest;
import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.service.Impl.RecipeServiceImpl;

@RunWith(SpringRunner.class)
public class RecipeServiceImplTest {

	@Mock
	RecipeServiceDao recipeServiceDao;

	@InjectMocks
	RecipeServiceImpl recipeServiceImpl;

	List<IngredientResponse> ingredientResponses = new ArrayList();
	List<RecipeResponse> recipeResponses = new ArrayList<>();
	IngredientResponse ingredientResponse = new IngredientResponse();
	RecipeResponse recipeResponse = new RecipeResponse();

	RecipeRequest recipeRequest = new RecipeRequest();
	IngredientRequest ingredientRequest = new IngredientRequest();
	List<IngredientRequest> ingredientRequests = new ArrayList<>();
	RecipeFilterCriteria recipeFilterCriteria = new RecipeFilterCriteria();

	private static final int CALORIES = 100;
	private static final String INSTRUTIONS = "Add eggs, flour, chocolate to pan. Bake at 350 for 1 hour";
	private static final String RECEIPE_NAME = "Chocolate Cake";
	private static final String RECEIPE_TYPE = "Veg";
	private static final String INGREDIENT_NAME = "Leite";
	private static final int INGREDIENT_ID = 1;

	private static final int NUMBER_SERVINGS = 3;

	@Before
	public void setup() {

		recipeResponse.setCalories(CALORIES);
		recipeResponse.setInstrutions(INSTRUTIONS);
		recipeResponse.setReceipeName(RECEIPE_NAME);
		recipeResponse.setReceipeType(RECEIPE_TYPE);
		recipeResponse.setServings(NUMBER_SERVINGS);

		ingredientResponse.setId(INGREDIENT_ID);
		ingredientResponse.setName(INGREDIENT_NAME);

		ingredientResponses.add(ingredientResponse);
		recipeResponse.setIngredientResponses(ingredientResponses);

		recipeRequest.setCalories(CALORIES);
		recipeRequest.setInstrutions(INSTRUTIONS);
		recipeRequest.setRecipeName(RECEIPE_NAME);
		recipeRequest.setRecipeType(RECEIPE_TYPE);
		recipeRequest.setServings(NUMBER_SERVINGS);
		ingredientRequest.setId(INGREDIENT_ID);
		ingredientRequest.setName(INGREDIENT_NAME);
		ingredientRequests.add(ingredientRequest);

		recipeFilterCriteria.setInstrutions(INSTRUTIONS);
		recipeFilterCriteria.setIngredients(INGREDIENT_NAME);
		recipeFilterCriteria.setReceipeType(RECEIPE_TYPE);
		recipeFilterCriteria.setServings(NUMBER_SERVINGS);

	}

	@Test
	public void getAllReceipesTest() throws RecordNotFoundException {

		when(recipeServiceDao.getAllReceipes()).thenReturn(recipeResponses);
		recipeServiceImpl.getAllReceipes();
	}

	@Test
	public void getRecipeByNameTest() throws RecordNotFoundException {
		when(recipeServiceDao.getRecipeByName(RECEIPE_NAME)).thenReturn(recipeResponse);
		recipeServiceImpl.getRecipeByName(RECEIPE_NAME);

	}

	@Test
	public void createRecipe() {
		when(recipeServiceDao.createRecipe(recipeRequest)).thenReturn(recipeResponse);
		recipeServiceImpl.createRecipe(recipeRequest);

		assertEquals(INSTRUTIONS, recipeRequest.getInstrutions());
		assertEquals(CALORIES, recipeRequest.getCalories());
		assertEquals(INSTRUTIONS, recipeRequest.getInstrutions());
		assertEquals(RECEIPE_NAME, recipeRequest.getRecipeName());
		assertEquals(RECEIPE_TYPE, recipeRequest.getRecipeType());

		assertEquals(Integer.valueOf(NUMBER_SERVINGS), recipeRequest.getServings());
		assertEquals(INGREDIENT_ID, ingredientRequest.getId());
		assertEquals(INGREDIENT_NAME, ingredientRequest.getName());

	}

	@Test
	public void deleteRecipeByNameTest() throws RecordNotFoundException {
		recipeServiceDao.deleteRecipeByName(RECEIPE_NAME);
		recipeServiceImpl.deleteRecipeByName(RECEIPE_NAME);

	}

	@Test
	public void deleteAllRecipeTest() {
		recipeServiceDao.deleteAllRecipe();
		recipeServiceImpl.deleteAllRecipe();
	}

	@Test
	public void updateRecipeTest() throws RecordNotFoundException {

		when(recipeServiceDao.updateRecipeByName(RECEIPE_NAME, recipeRequest)).thenReturn(recipeResponse);
		recipeServiceImpl.updateRecipe(RECEIPE_NAME, recipeRequest);
	}

	@Test(expected = RecordNotFoundException.class)
	public void getRecipeDetailsByFilterTest() throws RecordNotFoundException {
		when(recipeServiceDao.getRecipeByFilters(recipeFilterCriteria)).thenReturn(recipeResponses);
		recipeServiceImpl.getRecipeDetailsByFilter(recipeFilterCriteria);
	}

}
