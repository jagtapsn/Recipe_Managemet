package com.recipe.springbootrecipeapp.DAOImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.springbootrecipeapp.dao.impl.RecipeServiceDaoImpl;
import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.model.Ingredients;
import com.recipe.springbootrecipeapp.model.Recipe;
import com.recipe.springbootrecipeapp.repository.RecipeRepository;

@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:/testapplication.properties")
public class RecipeServiceDaoImplTest {

	@InjectMocks
	@Spy
	private RecipeServiceDaoImpl recipeServiceDaoImpl;

	@Mock
	private EntityManager entityManager;

	@Mock
	private EntityManagerFactory entityManagerFactory;

	@Mock
	private Selection<Object> selection;

	@Mock
	private Path<Object> path;

	@Mock
	RecipeRepository recipeRepository;

	private static final int CALORIES = 100;
	private static final String INSTRUTIONS = "Add eggs, flour, chocolate to pan. Bake at 350 for 1 hour";
	private static final String RECEIPE_NAME = "Chocolate Cake";
	private static final String RECEIPE_TYPE = "Veg";
	private static final String INGREDIENT_NAME = "Leite";
	private static final int INGREDIENT_ID = 1;

	private static final int NUMBER_SERVINGS = 3;
	List<Recipe> recipes = new ArrayList<>();
	Recipe recipe = new Recipe();

	RecipeFilterCriteria recipeFilterCriteria = new RecipeFilterCriteria();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setup() {

		recipeFilterCriteria.setInstrutions(INSTRUTIONS);
		recipeFilterCriteria.setIngredients(INGREDIENT_NAME);
		recipeFilterCriteria.setReceipeType(RECEIPE_TYPE);
		recipeFilterCriteria.setServings(NUMBER_SERVINGS);

		Ingredients ingredients = new Ingredients();
		ingredients.setId(INGREDIENT_ID);
		ingredients.setName(INGREDIENT_NAME);

		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredientsList.add(ingredients);

		recipe.setId(1);
		recipe.setName(RECEIPE_NAME);
		recipe.setCalories(CALORIES);
		recipe.setInstrutions(INSTRUTIONS);
		recipe.setRecipeType(RECEIPE_TYPE);
		recipe.setServings(NUMBER_SERVINGS);
		recipe.setIngredients(ingredientsList);
		recipes.add(recipe);

	}

	@Test
	public void getRecipeByFiltersTest() {
		CriteriaBuilder builder = mock(CriteriaBuilder.class);
		when(entityManager.getCriteriaBuilder()).thenReturn(builder);

		CriteriaQuery<Recipe> criteriaQuery = mock(CriteriaQuery.class);
		when(builder.createQuery(Recipe.class)).thenReturn(criteriaQuery);

		Root<Recipe> recipe = mock(Root.class);
		when(criteriaQuery.from(Recipe.class)).thenReturn(recipe);

		Join<Object, Object> ingredients = mock(Join.class);

		when(recipe.join("ingredients", javax.persistence.criteria.JoinType.INNER)).thenReturn(ingredients);

		Expression expression2 = mock(Expression.class);
		Predicate predicate = mock(Predicate.class);
		when(builder.and(any(Predicate.class))).thenReturn(predicate);
		when(expression2.in(any(String.class))).thenReturn(predicate);
		when(recipe.get(any(String.class))).thenReturn(path);
		when(ingredients.get(any(String.class))).thenReturn(path);

		when(criteriaQuery.select(any(Selection.class))).thenReturn(criteriaQuery);

		TypedQuery<Recipe> query = mock(TypedQuery.class);
		when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
		when(query.getResultList()).thenReturn(recipes);

		recipeServiceDaoImpl.getRecipeByFilters(recipeFilterCriteria);
	}

	@Test
	public void getAllReceipes() throws RecordNotFoundException {
		when(recipeRepository.findAll()).thenReturn(recipes);
		recipeServiceDaoImpl.getAllReceipes();
	}

	@Test
	public void getRecipeByNameTest() throws RecordNotFoundException {
		when(recipeRepository.findByName(RECEIPE_NAME)).thenReturn(recipe);
		recipeServiceDaoImpl.getRecipeByName(RECEIPE_NAME);
	}

	@Test
	public void deleteRecipeByNameTest() throws RecordNotFoundException {
		when(recipeRepository.findByName(RECEIPE_NAME)).thenReturn(recipe);
		recipeRepository.deleteById(recipe.getId());
		recipeServiceDaoImpl.deleteRecipeByName(RECEIPE_NAME);

	}

	@Test
	public void deleteAllRecipeTest() {
		recipeRepository.deleteAll();
		recipeServiceDaoImpl.deleteAllRecipe();

	}

}
