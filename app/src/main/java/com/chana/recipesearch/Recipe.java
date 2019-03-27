package com.chana.recipesearch;

public class Recipe {
	private String recipeName;
	private String id;
	private int rating;
	private String [] ingredients;
	private Object attributes;

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Recipe(String recipeName, String id, int rating, String [] ingredients, Object attributes) {

		this.recipeName = recipeName;
		this.id = id;
		this.rating = rating;
		this.ingredients = ingredients;
		this.attributes = attributes;
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

	public Object getAttributes(){return  attributes;}

}