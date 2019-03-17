package com.chana.recipesearch;

import static com.chana.recipesearch.FoodCategory.MILK;
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
        assertTrue(recipes.size() > 0);
    }

    @Test
    public void testGetAllRecipes() throws IOException {
        // given

        // when
        TestObserver<List<Recipe>> observer = recipeClient.getAllRecipes("lasagna", MILK)
                .test();

        // then
        List<Recipe> recipes = observer.values().get(0);
        assertEquals("Lasagna-2281977", recipes.get(0).getId());
        assertEquals("Miracle-Lasagna-Allrecipes", recipes.get(1).getId());
        assertEquals("Lasagna-2411433", recipes.get(2).getId());
        //assertEquals("Lasagna-Allrecipes", recipes.get(3).getId());
        assertEquals("Lasagna-Formaggio-1360903", recipes.get(3).getId());
        assertEquals("Lasagna-2003394", recipes.get(4).getId());
        assertEquals("Bertolli-lasagna-304071", recipes.get(5).getId());
        assertEquals("Lasagna-Rounds-1060438", recipes.get(6).getId());
        assertEquals("Spinach-Lasagna-Rolls-2254756", recipes.get(7).getId());
       // assertEquals("Easiest-Ever-Spinach-Lasagna-1953020", recipes.get(8).getId());
    }

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
