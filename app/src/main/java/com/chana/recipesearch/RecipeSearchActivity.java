package com.chana.recipesearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeSearchActivity extends AppCompatActivity {
    private EditText searchQuery;
    //private RadioGroup searchCategories;
    private RadioButton meat;
    private RadioButton milk;
    private RadioButton parve;
    private Button search;

    private RecipeClient client = new RecipeClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);

        searchQuery = findViewById(R.id.search_query);

        meat = findViewById(R.id.meat);
        milk = findViewById(R.id.milk);
        parve = findViewById(R.id.parve);

        search = findViewById(R.id.search_button);

        search.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipeResultsActivity.class);
            intent.putExtra("query", searchQuery.getText().toString());
            intent.putExtra("category", getCategory());
            this.startActivity(intent);
        });
    }

    private FoodCategory getCategory() {
        FoodCategory category = FoodCategory.KOSHER;
        if (meat.isChecked()){
            category = FoodCategory.MEAT;
        }
        if (milk.isChecked()){
            category = FoodCategory.MILK;
        }
        if (parve.isChecked()){
            category = FoodCategory.PARVE;
        }
        return category;
    }

}
