package com.chana.recipesearch;

import android.support.annotation.VisibleForTesting;

public enum Course {
    APPETIZERS("Appetizers"),
    SALADS("Salads"),
    SOUPS("Soups"),
    MAINS("Main Dishes"),
    SIDES("Side Dishes"),
    DESSERTS("Desserts");


    @VisibleForTesting
    private String  searchCriteria;

    public String  getSearchCriteria() {
        return searchCriteria;
    }

    @VisibleForTesting
    Course(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

}
