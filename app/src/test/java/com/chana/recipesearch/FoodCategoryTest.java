package com.chana.recipesearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.chana.recipesearch.FoodCategory.MEAT;
import static com.chana.recipesearch.FoodCategory.MILK;
import static com.chana.recipesearch.FoodCategory.PARVE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FoodCategoryTest {
    @Test
    public void testIsProhibitedTrue(){
        assertTrue(MILK.isProhibited("beef"));
    }

    @Test
    public void testIsArrayProhibitedTrue(){
        String [] ingredients = {"egg","butter cream","crust"};

        assertTrue(MEAT.isProhibited(ingredients));
    }

    @Test
    public void testIsProhibitedFalse(){
        assertFalse(MEAT.isProhibited("beef"));
    }

    @Test
    public void testIsArrayProhibitedFalse(){
        String [] ingredients = {"egg","butter","crust"};

        assertFalse(MILK.isProhibited(ingredients));
    }

    @Test
    public void testIsProhibitedParve(){
        assertTrue(PARVE.isProhibited("Beef"));
    }

    @Test
    public void testIsArrayProhibitedParve(){
        String [] ingredients = {"egg","butter","crust"};

        assertTrue(PARVE.isProhibited(ingredients));
    }

    @Test
    public void testFoodCategory(){

        FoodCategory PARVE = new FoodCategory(MILK, MEAT);

        List<String> parve = Arrays.asList("milk", "cream", "butter", "half and half", "cheese",
                "mozzarella", "parmesan", "beef", "chicken", "lamb", "meat", "turkey", "veal",
                "salami", "pastrami");

        assertTrue(PARVE.getProhibitedIngredients() = parve);
    }
}
