package com.recipe.springbootrecipeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.springbootrecipeapp.model.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
	List<Ingredients> findAllByOrderByName();
}