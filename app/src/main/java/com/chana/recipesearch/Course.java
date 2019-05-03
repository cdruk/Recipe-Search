package com.chana.recipesearch;

import android.support.annotation.VisibleForTesting;

public enum Course {
    APPETIZERS("Appetizers", R.drawable.appetizer),
    SALADS("Salads", R.drawable.salad),
    SOUPS("Soups", R.drawable.soup),
    MAINS("Main Dishes", R.drawable.main),
    SIDES("Side Dishes", R.drawable.side),
    DESSERTS("Desserts", R.drawable.dessert);


    @VisibleForTesting
    private String  searchCriteria;

    private int courseImage;

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public int getCourseImage() { return courseImage;}

    @VisibleForTesting
    Course(String searchCriteria, int courseImage) {
        this.searchCriteria = searchCriteria;
        this.courseImage = courseImage;
    }

}
