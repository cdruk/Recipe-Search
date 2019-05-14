package com.chana.recipesearch;


import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;
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
    private RecipeResultsAdapter mRecipeResultsAdapter;
    private RecipeClient client = new RecipeClient();

    private int start = 0;


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

        mRecipeResultsAdapter = new RecipeResultsAdapter( this);
        recyclerView.setAdapter(mRecipeResultsAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    start += 30;
                    requestRecipes(query,category,start);
                }
            }
        });

        requestRecipes(query, category, start);
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

    private void requestRecipes(String query, FoodCategory category, int start) {
        client.getSearchRecipes(query, category, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setSearchResults, this::onError);
    }

    private void setSearchResults(List<Recipe> recipes) {
        mRecipeResultsAdapter.getRecipes().addAll(recipes);
        mRecipeResultsAdapter.notifyDataSetChanged();

    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
