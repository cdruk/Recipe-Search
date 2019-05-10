package com.chana.recipesearch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

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

    private void setupActionBar(String query) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        title.setText(query);
    }

    private void setSearchResults(List<Recipe> recipes) {
        RecipeResultsAdapter mRecipeResultsAdapter = new RecipeResultsAdapter(recipes, this);
        recyclerView.setAdapter(mRecipeResultsAdapter);
    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
