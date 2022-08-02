package com.recipe.springbootrecipeapp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.springbootrecipeapp.dao.IngredientServiceDao;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.service.Impl.IngredientServiceImpl;

@RunWith(SpringRunner.class)
public class IngredientServiceImplTest {

	@Mock
	IngredientServiceDao indIngredientServiceDao;

	@InjectMocks
	IngredientServiceImpl ingredientServiceImpl;

	List<IngredientResponse> ingredientResponses = new ArrayList<>();
	private static final String INGREDIENT_NAME = "Leite";
	private static final int INGREDIENT_ID = 1;

	@Before
	public void setup() {

		IngredientResponse ingredients = new IngredientResponse();
		ingredients.setId(INGREDIENT_ID);
		ingredients.setName(INGREDIENT_NAME);
		ingredientResponses.add(ingredients);
	}

	@Test
	public void getAllIngredientTest() throws RecordNotFoundException {
		when(indIngredientServiceDao.getAllIngrdient()).thenReturn(ingredientResponses);
		ingredientServiceImpl.getAllIngredient();
	}

}
