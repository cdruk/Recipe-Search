package com.chana.recipesearch;

import com.google.gson.internal.LinkedTreeMap;

public class RecipeModel {

	private Attribution attribution;
	private String[] ingredientLines;
	private Object[] images;

	public RecipeModel(Attribution attribution, String[] ingredientLines, String [] images) {
		super();
		this.attribution = attribution;
		this.ingredientLines = ingredientLines;
		this.images = images;
	}

	public Attribution getAttribution() {
		return attribution;
	}

	public String[] getIngredientLines() {
		return ingredientLines;
	}

	public String getFormatedIngredients(){
	    StringBuilder ingredients = new StringBuilder();
        for (String ingredient:ingredientLines) {
            ingredients.append(ingredient + "\n");
        }
        return ingredients.toString();
    }

	public String getImage() {
        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) images[0];
	    return linkedTreeMap.get("hostedLargeUrl").toString();}

}
