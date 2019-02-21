package com.chana.recipesearch;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chana.recipesearch.Categories.*;

public class RecipeClient {

    private Information information = new Information();
    private String[] allNotKosher = information.getAllNotKosher();
    private String[] allMeat = information.getAllMeat();
    private String[] allMilk = information.getAllMilk();


    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.yummly.com/v1/api/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    RecipeService service = retrofit.create(RecipeService.class);

    Single<List<Recipe>> getAllRecipes(String recipeQuery, Enum category) {
        return service.getAllRecipes(recipeQuery)
                .map(RecipeFeedModel::getMatches)
                .map(list -> {
                    for (Recipe recipe : list) {
                        // loop through the recipes, get ingredients, remove anything that isn't kosher
                        String[] ingredients = recipe.getIngredients();
                        boolean notOk = false;
                        for (String ingredient : ingredients) {
                            //I have a error with my switch because
                            //it doesn't like my Enum and wants an enum instead.
                            switch (category) {
                                case MEAT:
                                    for (String notKosher : allNotKosher) {
                                        notOk = ingredient.toLowerCase().contains(notKosher);
                                    }
                                    for (String meat : allMeat) {
                                        notOk = ingredient.toLowerCase().contains(meat);
                                    }
                                case MILK:
                                    for (String notKosher : allNotKosher) {
                                        notOk = ingredient.toLowerCase().contains(notKosher);
                                    }
                                    for (String milk : allMilk) {
                                        notOk = ingredient.toLowerCase().contains(milk);
                                    }
                                case PARVE:
                                    for (String notKosher : allNotKosher) {
                                        notOk = ingredient.toLowerCase().contains(notKosher);
                                    }
                                    for (String meat : allMeat) {
                                        notOk = ingredient.toLowerCase().contains(meat);
                                    }
                                    for (String milk : allMilk) {
                                        notOk = ingredient.toLowerCase().contains(milk);
                                    }
                                default:
                                    for (String notKosher : allNotKosher) {
                                        notOk = ingredient.toLowerCase().contains(notKosher);
                                    }
                            }
                            if (!notOk) {
                                list.add(recipe);
                            }
                        }
                    }
                    return list;
                });
    }

    Single<RecipeModel> getRecipeDetails(String recipeId) {
        return service.getRecipeDetails(recipeId)
                .map(RecipeModel::getIngredientLines)
                .map(list -> {
                    RecipeModel recipeModel = new RecipeModel();
                    list.add(recipeModel);
                    return list;
                });
    }

}
