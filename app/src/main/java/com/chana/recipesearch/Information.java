package com.chana.recipesearch;

public class Information {

    public static final String[] allNotKosher = {"shrimp", "crab", "lobster", "clam", "oyster",
                                    "bacon", "pork", "ham", "pepperoni", "sausage"};

    private String[] allMilk = {"milk", "cream", "butter", "half and half", "cheese",
                                "mozzarella", "parmesan"};

    private String[] allMeat = {"beef", "chicken", "lamb", "meat", "turkey", "veal",
                                "salami", "pastrami"};

    public String[] getAllNotKosher() {
        return allNotKosher;
    }

    public String[] getAllMilk() {
        return allMilk;
    }

    public String[] getAllMeat() {
        return allMeat;
    }
}
