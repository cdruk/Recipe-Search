package com.chana.recipesearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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
        setupActionBar();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar() {
        @NonNull ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.find_a_recipe_for);
    }

    private FoodCategory getCategory() {
        FoodCategory category = FoodCategory.KOSHER;
        if (meat.isChecked()) {
            category = FoodCategory.MEAT;
        }
        if (milk.isChecked()) {
            category = FoodCategory.MILK;
        }
        if (parve.isChecked()) {
            category = FoodCategory.PARVE;
        }
        return category;
    }

}
