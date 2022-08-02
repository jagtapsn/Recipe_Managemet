package com.recipe.springbootrecipeapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "servings")
    private int servings;
	
	@Column(name = "calories")
    private int calories;
	
	@ManyToMany
    private List<Ingredients> ingredients;
	
	@Column(name = "instrutions")
    private String instrutions;
	
	@Column(name = "type")
	private String recipeType;
    
	public Recipe() {
	}

	public Recipe(String name, int servings, int calories, List<Ingredients> ingredients, String instrutions,
			String recipeType) {
		super();
		this.name = name;
		this.servings = servings;
		this.calories = calories;
		this.ingredients = ingredients;
		this.instrutions = instrutions;
		this.recipeType = recipeType;
	}
	
	
	
	
}