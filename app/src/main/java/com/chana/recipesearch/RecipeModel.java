package com.chana.recipesearch;

import com.google.gson.internal.LinkedTreeMap;


public class RecipeModel {

	private Source source;
	private String[] ingredientLines;
	private Object[] images;

	public RecipeModel(Source source, String[] ingredientLines, String [] images) {
		super();
		this.source = source;
		this.ingredientLines = ingredientLines;
		this.images = images;
	}

	public Source getSource() {
		return source;
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
