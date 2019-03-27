package com.chana.recipesearch;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chana.recipesearch.FoodCategory.KOSHER;
import static com.chana.recipesearch.FoodCategory.MILK;

public class RecipeClient {


    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.yummly.com/v1/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    RecipeService service = retrofit.create(RecipeService.class);

    Single<List<Recipe>> getRandomRecipe() {
        return service.getRandomRecipe()
                .map(RecipeFeedModel::getMatches);
    }

    Single<List<Recipe>> getSearchRecipes(String recipeQuery, FoodCategory category) {
        return service.getSearchRecipes(recipeQuery, category.excluded())
                .map(RecipeFeedModel::getMatches)
                .map(list -> {
                    List<Recipe> kosherRecipes = new ArrayList<Recipe>();
                            for (Recipe recipe : list) {
                                // loop through the recipes, get ingredients, remove anything that isn't kosher
                                String[] ingredients = recipe.getIngredients();
                                if (!KOSHER.isProhibited(ingredients) && !category.isProhibited(ingredients)) {
                                    kosherRecipes.add(recipe);
                                }
                            }
                            return kosherRecipes;
                        }
                );
    }

    Single<List<Recipe>> getCourseRecipes(Course course) {
        return service.getCourseRecipes(course.getSearchCriteria(), KOSHER.excluded(), 20)
                .map(RecipeFeedModel::getMatches)
                .map(list -> {
                            List<Recipe> kosherRecipes = new ArrayList<Recipe>();
                            for (Recipe recipe : list) {
                                // loop through the recipes, get ingredients, remove anything that isn't kosher
                                String[] ingredients = recipe.getIngredients();
                                if (!KOSHER.isProhibited(ingredients) && !KOSHER.isMilkAndMeat(ingredients)) {
                                    kosherRecipes.add(recipe);
                                }
                            }
                            return kosherRecipes;
                        }
                );
    }

    Single<RecipeModel> getRecipeDetails(String recipeId) {
        return service.getRecipeDetails(recipeId);
    }

}
