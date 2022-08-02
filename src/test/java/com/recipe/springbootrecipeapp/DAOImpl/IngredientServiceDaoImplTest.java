package com.recipe.springbootrecipeapp.DAOImpl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.springbootrecipeapp.dao.impl.IngredientServiceDaoImpl;
import com.recipe.springbootrecipeapp.model.Ingredients;
import com.recipe.springbootrecipeapp.repository.IngredientsRepository;

@RunWith(SpringRunner.class)
public class IngredientServiceDaoImplTest {

	@InjectMocks
	@Spy
	private IngredientServiceDaoImpl ingredientServiceDaoImpl;

	@Mock
	IngredientsRepository ingredientsRepository;

	private static final String INSTRUTIONS = "Add eggs, flour, chocolate to pan. Bake at 350 for 1 hour";
	private static final String INGREDIENT_NAME = "Leite";
	private static final int INGREDIENT_ID = 1;
	List<Ingredients> ingredientsList = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setup() {

		Ingredients ingredients = new Ingredients();
		ingredients.setId(INGREDIENT_ID);
		ingredients.setName(INGREDIENT_NAME);
		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredientsList.add(ingredients);
	}

	@Test
	public void getAllIngrdientTest() {

		when(ingredientsRepository.findAllByOrderByName()).thenReturn(ingredientsList);
		ingredientServiceDaoImpl.getAllIngrdient();

	}

}
