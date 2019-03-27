package com.chana.recipesearch;

import android.support.annotation.VisibleForTesting;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum FoodCategory {
    // contains a list of milk products
    MEAT("milk", "cream", "butter", "half and half", "cheese",
            "mozzarella", "parmesan"),
    MILK("beef", "chicken", "lamb", "meat", "turkey", "veal",
            "salami", "pastrami"),
    PARVE(MEAT, MILK),
    KOSHER("shrimp", "crab", "lobster", "clam", "oyster",
            "bacon", "pork", "ham", "pepperoni", "sausage");


    @VisibleForTesting
    private List<String> prohibitedIngredients;

    public List<String> getProhibitedIngredients() {
        return prohibitedIngredients;
    }

    @VisibleForTesting
    FoodCategory(String... prohibitedIngredients) {
        this.prohibitedIngredients = Arrays.asList(prohibitedIngredients);
    }

    @VisibleForTesting
    FoodCategory(FoodCategory a, FoodCategory b) {
        this.prohibitedIngredients = new ArrayList<>();
        this.prohibitedIngredients.addAll(a.prohibitedIngredients);
        this.prohibitedIngredients.addAll(b.prohibitedIngredients);
    }

    public boolean isProhibited(String ingredient) {
        for (String prohibitedIngredient : prohibitedIngredients) {
            Pattern p = Pattern.compile("\\b" + prohibitedIngredient + "\\b", Pattern.CASE_INSENSITIVE);
            if (p.matcher(ingredient).find()) {
                return true;
            }
        }
        return false;
    }

    public boolean isProhibited(String[] ingredients) {
        for (String ingredient : ingredients) {
            if (isProhibited(ingredient)) {
                return true;
            }
        }
        return false;
    }

    public String[] excluded() {
        ArrayList<String> excluded = new ArrayList(KOSHER.prohibitedIngredients);
        excluded.addAll(prohibitedIngredients);

        return excluded.toArray(new String[0]);
    }

    public boolean isMilk(String ingredient) {
        for (String prohibitedIngredient : MEAT.prohibitedIngredients) {
            Pattern p = Pattern.compile("\\b" + prohibitedIngredient + "\\b", Pattern.CASE_INSENSITIVE);
            if (p.matcher(ingredient).find()) {
                return true;
            }
        }
        return false;
    }

    public boolean isMeat(String ingredient) {
        for (String prohibitedIngredient : MILK.prohibitedIngredients) {
            Pattern p = Pattern.compile("\\b" + prohibitedIngredient + "\\b", Pattern.CASE_INSENSITIVE);
            if (p.matcher(ingredient).find()) {
                return true;
            }
        }
        return false;
    }

    public boolean isMilkAndMeat(String[] ingredients) {
        boolean milk = false;
        boolean meat = false;
        for (String ingredient : ingredients) {
            if (isMilk(ingredient)) {
                milk = true;
            }
            if (isMeat(ingredient)){
                meat = true;
            }
        }
        return milk && meat;
    }
}
