package com.chana.recipesearch;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecipeClient client = new RecipeClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_results);
        recyclerView = findViewById(R.id.recipe_results_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        String query = getIntent().getStringExtra("query");
        FoodCategory category = (FoodCategory) getIntent().getSerializableExtra("category");

        setupActionBar(query);

        client.getSearchRecipes(query, category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setSearchResults, this::onError);
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

    private void setupActionBar(String query) {
        @NonNull ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(query);
    }

    private void setSearchResults(List<Recipe> recipes) {
        RecipeResultsAdapter mRecipeResultsAdapter = new RecipeResultsAdapter(recipes, this);
        recyclerView.setAdapter(mRecipeResultsAdapter);
    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
