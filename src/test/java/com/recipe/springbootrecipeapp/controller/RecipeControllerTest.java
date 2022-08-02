package com.recipe.springbootrecipeapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.springbootrecipeapp.dto.request.IngredientRequest;
import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.service.IngredientService;
import com.recipe.springbootrecipeapp.service.RecipeService;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RecipeService recipeService;

	@MockBean
	IngredientService ingredientService;
	List<IngredientResponse> ingredientResponses = new ArrayList();
	List<RecipeResponse> recipeResponses = new ArrayList<>();
	IngredientResponse ingredientResponse = new IngredientResponse();
	RecipeResponse recipeResponse = new RecipeResponse();

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
	}

	@Test
	public void getIngrendientsOrderByNameTest() throws Exception {

		when(ingredientService.getAllIngredient()).thenReturn(ingredientResponses);

		ResultActions result = mockMvc.perform(get("/api/ingredients").contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());

		assertEquals(INGREDIENT_NAME, ingredientResponse.getName());
		assertEquals(INGREDIENT_ID, ingredientResponse.getId());
	}

	@Test
	public void getRecipeByFilterTest() throws Exception {
		RecipeFilterCriteria recipeFilterCriteria = new RecipeFilterCriteria();
		recipeFilterCriteria.setInstrutions(INSTRUTIONS);
		recipeFilterCriteria.setIngredients(INGREDIENT_NAME);
		recipeFilterCriteria.setReceipeType(RECEIPE_TYPE);
		recipeFilterCriteria.setServings(NUMBER_SERVINGS);

		when(recipeService.getRecipeDetailsByFilter(recipeFilterCriteria)).thenReturn(recipeResponses);

		ResultActions result = mockMvc
				.perform(get("/api/recipesfilters").contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());

		assertEquals(INSTRUTIONS, recipeFilterCriteria.getInstrutions());
		assertEquals(INGREDIENT_NAME, recipeFilterCriteria.getIngredients());
		assertEquals(RECEIPE_TYPE, recipeFilterCriteria.getReceipeType());
		assertEquals(Integer.valueOf(NUMBER_SERVINGS), recipeFilterCriteria.getServings());

	}

	@Test
	public void getAllRecipeTest() throws Exception {

		when(recipeService.getAllReceipes()).thenReturn(recipeResponses);
		ResultActions result = mockMvc.perform(get("/api/recipes").contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

	@Test
	public void getRecipeByNameTest() throws Exception {

		when(recipeService.getRecipeByName(RECEIPE_NAME)).thenReturn(recipeResponse);
		ResultActions result = mockMvc
				.perform(get("/api/recipes/{name}", RECEIPE_NAME).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
	}

	@Test
	public void postRecipeTest() throws Exception {
		IngredientRequest ingredients = new IngredientRequest();
		ingredients.setId(INGREDIENT_ID);
		ingredients.setName(INGREDIENT_NAME);

		List<IngredientRequest> ingredientsList = new ArrayList<>();
		ingredientsList.add(ingredients);

		RecipeRequest recipe = new RecipeRequest();
		recipe.setRecipeName(RECEIPE_NAME);
		recipe.setCalories(CALORIES);
		recipe.setInstrutions(INSTRUTIONS);
		recipe.setRecipeType(RECEIPE_TYPE);
		recipe.setServings(NUMBER_SERVINGS);
		recipe.setIngredients(ingredientsList);

		String recipeStr = mapToJson(recipe);
		when(recipeService.createRecipe(recipe)).thenReturn(recipeResponse);
		ResultActions result = mockMvc
				.perform(post("/api/recipes/create").content(recipeStr).contentType(MediaType.APPLICATION_JSON_VALUE));
		result.andExpect(status().isOk());
		assertEquals(RECEIPE_NAME, recipe.getRecipeName());
		assertEquals(CALORIES, recipe.getCalories());
		assertEquals(INSTRUTIONS, recipe.getInstrutions());
		assertEquals(RECEIPE_TYPE, recipe.getRecipeType());
		// assertEquals(NUMBER_SERVINGS, recipe.getServings());

		assertEquals(INGREDIENT_ID, ingredients.getId());
		assertEquals(INGREDIENT_NAME, ingredients.getName());

		assertEquals(ingredientsList, recipe.getIngredients());

	}

	@Test
	public void deleteRecipeByNameTest() throws Exception {

		String uri = "/api/recipes/Chocolate_Cake";
		MvcResult mvcResult = mockMvc.perform(delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Recipe has been deleted!");
	}

	@Test
	public void deleteRecipeTest() throws Exception {

		String uri = "/api/recipes/delete";
		MvcResult mvcResult = mockMvc.perform(delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "All recipes have been deleted!");
	}

	@Test
	public void UpdateRecipeTest() throws Exception {

		String uri = "/api/recipes/Chocolate_Cake";
		RecipeRequest recipe = new RecipeRequest();
		recipe.setRecipeName(RECEIPE_NAME);
		recipe.setCalories(CALORIES);
		recipe.setInstrutions(INSTRUTIONS);
		String recipeStr = mapToJson(recipe);

		when(recipeService.updateRecipe(RECEIPE_NAME, recipe)).thenReturn(recipeResponse);
		MvcResult mvcResult = mockMvc.perform(put(uri).content(recipeStr).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
