package com.chana.recipesearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

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

    private void setupActionBar(String recipeName) {
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        title.setText(recipeName);
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
