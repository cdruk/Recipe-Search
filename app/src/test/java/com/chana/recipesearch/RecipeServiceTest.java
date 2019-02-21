package com.chana.recipesearch;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.chana.recipesearch.Recipe;
import com.chana.recipesearch.RecipeFeedModel;
import com.chana.recipesearch.RecipeService;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceTest {

//	Retrofit retrofit = new Retrofit.Builder()
//			.baseUrl("http://api.yummly.com/v1/api/")
//			.addConverterFactory(GsonConverterFactory.create())
//			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//			.build();
//
//	RecipeService service = retrofit.create(RecipeService.class);

	RecipeClient recipeClient = new RecipeClient();

    @Test
	public void testGetRandomRecipe() throws IOException {
		// given

		// when
		TestObserver<List<Recipe>> observer = recipeClient.getRandomRecipe()
				.test();

		// then
        List<Recipe> recipes = observer.values().get(0);
		//List<Recipe> details = recipes.getMatches();
		assertTrue(recipes.size() > 0);
		//String recipeName = details.get(0).getRecipeName();
		//assertNotNull(recipeName.getName());
	}

//	@Test
//	public void testGetAllRecipies() throws IOException {
//		// given
//
//		// when
//		TestObserver<List<Recipe>> observer = recipeClient.getAllRecipes(null, null)
//				.test();
//
//		// then
//        List<Recipe> recipes = observer.values().get(0);
//		List<Recipe> details = recipes.getMatches();
//		assertTrue(details.size() > 0);
//		//String recipeName = details.get(0).getRecipeName();
//		//assertNotNull(recipeName.getName());
//	}
	
//	@Test
//	public void testGetRecipeDetails() throws IOException {
//		// given
//
//		// when
//        TestObserver<RecipeModel> observer = service.getRecipeDetails("Lasagna-494544")
//                .test();
//
//		// then
//        RecipeModel model = observer.values().get(0);
//		Attribution details = model.getAttribution();
//		assertNotNull(details);
//		//String recipeName = details.get(0).getRecipeName();
//		//assertNotNull(recipeName.getName());
//	}

}
