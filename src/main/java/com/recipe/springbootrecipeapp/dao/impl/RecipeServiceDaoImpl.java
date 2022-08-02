package com.recipe.springbootrecipeapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.recipe.springbootrecipeapp.dao.RecipeServiceDao;
import com.recipe.springbootrecipeapp.dto.request.RecipeFilterCriteria;
import com.recipe.springbootrecipeapp.dto.request.RecipeRequest;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.dto.response.RecipeResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.model.Ingredients;
import com.recipe.springbootrecipeapp.model.Recipe;
import com.recipe.springbootrecipeapp.repository.RecipeRepository;

@Service
public class RecipeServiceDaoImpl implements RecipeServiceDao {

	private EntityManager sessionFactory;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	ModelMapper modelMapper;

	public RecipeServiceDaoImpl(@Qualifier("entityManagerFactory") EntityManager sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@Override
	public List<RecipeResponse> getRecipeByFilters(RecipeFilterCriteria recipeFilterCriteria) {
		CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
		Root<Recipe> root = criteriaQuery.from(Recipe.class);
		Join<Recipe, Ingredients> ingredients = root.join("ingredients", javax.persistence.criteria.JoinType.INNER);
		List<Predicate> predicates = preparePredicated(root, recipeFilterCriteria, ingredients);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[] {})));
		criteriaQuery.distinct(true);
		List<Recipe> recipes = sessionFactory.createQuery(criteriaQuery).getResultList();
		List<RecipeResponse> recipeResponses = mapToDTO(recipes);
		return recipeResponses;
	}

	private List<RecipeResponse> mapToDTO(List<Recipe> recipes) {
		List<RecipeResponse> recipeResponses = new ArrayList<>();
		recipes.forEach(recipe -> {
			RecipeResponse recipeResponse = new RecipeResponse();
			recipeResponse.setInstrutions(recipe.getInstrutions());
			List<IngredientResponse> IngredientResponses = new ArrayList<>();
			recipe.getIngredients().forEach(ingredients -> {
				IngredientResponse ingredientResponse = new IngredientResponse();
				ingredientResponse.setId(ingredients.getId());
				ingredientResponse.setName(ingredients.getName());
				IngredientResponses.add(ingredientResponse);
			});
			recipeResponse.setIngredientResponses(IngredientResponses);
			recipeResponse.setInstrutions(recipe.getInstrutions());
			recipeResponse.setReceipeName(recipe.getName());
			recipeResponse.setReceipeType(recipe.getRecipeType());
			recipeResponse.setCalories(recipe.getCalories());
			recipeResponse.setServings(recipe.getCalories());
			recipeResponses.add(recipeResponse);
		});
		return recipeResponses;

	}

	private List<Predicate> preparePredicated(Root<Recipe> root, RecipeFilterCriteria recipeFilterCriteria,
			Join<Recipe, Ingredients> ingredients) {
		List<Predicate> predicates = new ArrayList<>();
		if (recipeFilterCriteria.getReceipeType() != null) {
			predicates.add(root.get("recipeType").in(recipeFilterCriteria.getReceipeType()));
		}

		if (recipeFilterCriteria.getServings() != null) {
			predicates.add(root.get("servings").in(recipeFilterCriteria.getServings()));
		}

		if (recipeFilterCriteria.getInstrutions() != null) {
			predicates.add(root.get("instrutions").in(recipeFilterCriteria.getInstrutions()));
		}

		if (recipeFilterCriteria.getIngredients() != null) {
			predicates.add(ingredients.get("name").in(recipeFilterCriteria.getIngredients()));
		}
		return predicates;

	}

	@Override
	public List<RecipeResponse> getAllReceipes() throws RecordNotFoundException {
		List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
		if (!recipes.isEmpty())
			return mapToDTO(recipes);
		;
		throw new RecordNotFoundException("Record Not found");

	}

	@Override
	public RecipeResponse getRecipeByName(String name) throws RecordNotFoundException {
		Recipe recipe = recipeRepository.findByName(name);

		if (recipe != null)
			return mapEntityToDTO(recipe);

		throw new RecordNotFoundException("Record Not found");
	}

	private RecipeResponse mapEntityToDTO(Recipe recipe) {
		RecipeResponse recipeResponse = new RecipeResponse();
		recipeResponse.setReceipeName(recipe.getName());
		recipeResponse.setCalories(recipe.getCalories());
		recipeResponse.setReceipeType(recipe.getRecipeType());
		recipeResponse.setServings(recipe.getCalories());
		recipeResponse.setInstrutions(recipe.getInstrutions());
		List<IngredientResponse> IngredientResponses = new ArrayList<>();
		recipe.getIngredients().forEach(ingredients -> {
			IngredientResponse ingredientResponse = new IngredientResponse();
			ingredientResponse.setId(ingredients.getId());
			ingredientResponse.setName(ingredients.getName());
			IngredientResponses.add(ingredientResponse);
		});
		recipeResponse.setIngredientResponses(IngredientResponses);
		return recipeResponse;
	}

	@Override
	public RecipeResponse createRecipe(RecipeRequest recipeRequest) {

		Recipe _recipe = recipeRepository.save(mapDTOToEntity(recipeRequest));
		return mapEntityToDTO(_recipe);

	}

	private Recipe mapDTOToEntity(RecipeRequest recipeRequest) {

		Recipe recipe = new Recipe();
		recipe.setName(recipeRequest.getRecipeName());
		recipe.setCalories(recipeRequest.getCalories());
		recipe.setRecipeType(recipeRequest.getRecipeType());
		recipe.setServings(recipeRequest.getCalories());
		recipe.setInstrutions(recipeRequest.getInstrutions());
		List<Ingredients> Ingredients = new ArrayList<>();
		recipeRequest.getIngredients().forEach(ingredients -> {
			Ingredients ingredient = new Ingredients();
			ingredient.setId(ingredients.getId());
			ingredient.setName(ingredients.getName());
			Ingredients.add(ingredient);
		});
		recipe.setIngredients(Ingredients);
		return recipe;

	}

	@Override
	public void deleteRecipeByName(String name) throws RecordNotFoundException {
		Recipe recipe = recipeRepository.findByName(name);
		if (recipe != null)
			recipeRepository.deleteById(recipe.getId());
		else
			throw new RecordNotFoundException("Record Not Found");

	}

	@Override
	public void deleteAllRecipe() {
		recipeRepository.deleteAll();

	}

	@Override
	public RecipeResponse updateRecipeByName(String name, RecipeRequest recipeRequest) throws RecordNotFoundException {
		Recipe recipe = mapDTOToEntity(recipeRequest);
		Recipe _recipe = recipeRepository.findByName(recipeRequest.getRecipeName());
		if (_recipe != null) {
			_recipe.setName(recipe.getName());
			_recipe.setServings(recipe.getServings());
			_recipe.setCalories(recipe.getCalories());
			_recipe.setIngredients(recipe.getIngredients());
			_recipe.setInstrutions(recipe.getInstrutions());
			recipeRepository.save(_recipe);
		} else {
			throw new RecordNotFoundException("Record Not found");
		}
		return mapEntityToDTO(_recipe);
	}
}
