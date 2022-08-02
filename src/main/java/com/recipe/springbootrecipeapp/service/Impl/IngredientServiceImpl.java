package com.recipe.springbootrecipeapp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.springbootrecipeapp.dao.IngredientServiceDao;
import com.recipe.springbootrecipeapp.dto.response.IngredientResponse;
import com.recipe.springbootrecipeapp.exceptions.RecordNotFoundException;
import com.recipe.springbootrecipeapp.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	IngredientServiceDao indIngredientServiceDao;

	@Override
	public List<IngredientResponse> getAllIngredient() throws RecordNotFoundException {
		return indIngredientServiceDao.getAllIngrdient();

	}

}
