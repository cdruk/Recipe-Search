package com.chana.recipesearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeDetailsActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private TextView ingredients;
    private Button viewRecipeButton;

    private String recipeId;

    private RecipeClient client = new RecipeClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeImage = findViewById(R.id.recipe_detail_image);
        ingredients = findViewById(R.id.ingredient_list);
        viewRecipeButton = findViewById(R.id.full_recipe_button);

        recipeId = getIntent().getStringExtra("recipe_id");

        setupActionBar(getIntent().getStringExtra("recipe_name"));

        client.getRecipeDetails(recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRecipeDetails, this::onError);
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

    private void setupActionBar(String recipeName) {
        @NonNull ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(recipeName);
    }

    private void setRecipeDetails(RecipeModel recipeModel) {
        ingredients.setText(recipeModel.getFormatedIngredients());
        Glide.with(this).load(recipeModel.getImage()).fitCenter().into(recipeImage);
        viewRecipeButton.setOnClickListener(view -> {
            Intent browserIntent =
                    new Intent(Intent.ACTION_VIEW, Uri.parse(recipeModel.getSource().getSourceRecipeUrl()));
            startActivity(browserIntent);
        });
    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
