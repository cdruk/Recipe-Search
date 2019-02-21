package com.chana.recipesearch;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FoodCategory {
    // contains a list of milk products
    MEAT("milk", "cream", "butter", "half and half", "cheese",
            "mozzarella", "parmesan"),
    MILK("beef", "chicken", "lamb", "meat", "turkey", "veal",
            "salami", "pastrami"),
    PARVE(MEAT, MILK),
    KOSHER("shrimp", "crab", "lobster", "clam", "oyster",
            "bacon", "pork", "ham", "pepperoni", "sausage");

    private List<String> prohibitedIngredients;

    FoodCategory(String... prohibitedIngredients) {
        this.prohibitedIngredients = Arrays.asList(prohibitedIngredients);
    }

    FoodCategory(FoodCategory a, FoodCategory b) {
        this.prohibitedIngredients = new ArrayList<>();
        this.prohibitedIngredients.addAll(a.prohibitedIngredients);
        this.prohibitedIngredients.addAll(b.prohibitedIngredients);
    }

    public boolean isProhibited(String ingredient) {
       return this.prohibitedIngredients.contains(ingredient);
    }

    public boolean isProhibited(String[] ingredients) {
        for (String ingredient:ingredients) {
            if (isProhibited(ingredient)){
                return true;
            }
        }
        return false;
    }
}
