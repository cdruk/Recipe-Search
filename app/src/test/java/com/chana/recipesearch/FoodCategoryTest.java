package com.chana.recipesearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.chana.recipesearch.FoodCategory.KOSHER;
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
    public void testIsProhibitedSubString(){
        String ingredient = "graham cracker crust";

        assertFalse(KOSHER.isProhibited(ingredient));
    }

    @Test
    public void testFoodCategory(){

        assertTrue(PARVE.getProhibitedIngredients().containsAll(MILK.getProhibitedIngredients()));
        assertTrue(PARVE.getProhibitedIngredients().containsAll(MEAT.getProhibitedIngredients()));

    }

    @Test
    public void testIsMeat(){
        assertTrue(KOSHER.isMeat("beef"));
    }

    @Test
    public void testIsMilk(){
        assertTrue(KOSHER.isMilk("ice cream"));
    }

    @Test
    public void testIsMeatAndMilk(){
        String [] ingredients = {"egg","mozzarella","beef mince"};
        assertTrue(KOSHER.isMilkAndMeat(ingredients));
    }

    @Test
    public void testIsNotMeatAndMilk(){
        String [] ingredients = {"egg","butter","cheese"};
        assertFalse(KOSHER.isMilkAndMeat(ingredients));
    }

    @Test
    public void isCapitalizedMeatAndMilk(){
        String [] ingredients = {"Queso", "meatballs"};
        assertTrue(KOSHER.isMilkAndMeat(ingredients));
    }
}
