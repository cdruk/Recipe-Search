package com.chana.recipesearch;

import static com.chana.recipesearch.FoodCategory.KOSHER;
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

public class RecipeClientTest {

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
    public void testGetSearchRecipes() throws IOException {
        // given

        // when
        TestObserver<List<Recipe>> observer = recipeClient.getSearchRecipes("lasagna", MILK)
                .test();

        // then
        List<Recipe> recipes = observer.values().get(0);
        assertEquals("Lasagna-2281977", recipes.get(0).getId());
        assertEquals("Miracle-Lasagna-Allrecipes", recipes.get(1).getId());
        assertEquals("Lasagna-2411433", recipes.get(2).getId());
        assertEquals("Lasagna-Formaggio-1360903", recipes.get(3).getId());
        assertEquals("Lasagna-2003394", recipes.get(4).getId());
        assertEquals("Bertolli-lasagna-304071", recipes.get(5).getId());
        assertEquals("Lasagna-Rounds-1060438", recipes.get(6).getId());
        assertEquals("Spinach-Lasagna-Rolls-2254756", recipes.get(7).getId());
    }

    @Test
    public void testGetCourseRecipes() throws IOException {
        // given

        // when
        TestObserver<List<Recipe>> observer = recipeClient.getCourseRecipes (Course.APPETIZERS)
                .test();

        // then
        List<Recipe> recipes = observer.values().get(0);
        assertNotNull(recipes);
    }

	@Test
	public void testGetRecipeDetails() throws IOException {
		// given

		// when
        TestObserver<RecipeModel> observer = recipeClient.getRecipeDetails("Lasagna-494544")
                .test();

		// then
        RecipeModel model = observer.values().get(0);
		Attribution details = model.getAttribution();
		assertNotNull(details);
	}

}
