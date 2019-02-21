package com.chana.recipesearch;

public class Recipe {
	private String recipeName;
	private String id;
	private int rating;
	private String [] ingredients;
	
	public Recipe(String recipeName, String id, int rating, String [] ingredients) {

		this.recipeName = recipeName;
		this.id = id;
		this.rating = rating;
		this.ingredients = ingredients;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}

	public String [] getIngredients() {return ingredients;}

}