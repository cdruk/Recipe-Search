package com.chana.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeResultsAdapter extends RecyclerView.Adapter<RecipeResultsAdapter.RecipeResultsViewHolder>{

    private List<Recipe> recipes;
    Context context;

    public RecipeResultsAdapter(List<Recipe> recipes, Context context){
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeResultsAdapter.RecipeResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_layout, parent, false);
        RecipeResultsAdapter.RecipeResultsViewHolder recipeResultsViewHolder =
                new RecipeResultsAdapter.RecipeResultsViewHolder(view, context, recipes);
        return recipeResultsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeResultsAdapter.RecipeResultsViewHolder holder, int position) {
        String recipe_image = recipes.get(position).getImage();
        Glide.with(context)
                .load(recipe_image).into(holder.recipe_image);

        holder.recipeName.setText( recipes.get(position).getRecipeName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class  RecipeResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recipe_image;
        TextView recipeName;
        List<Recipe> recipes;
        Context context;

        public RecipeResultsViewHolder(@NonNull View itemView, Context context, List<Recipe> recipes) {
            super(itemView);
            this.recipes = recipes;
            this.context = context;
            itemView.setOnClickListener(this);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Recipe recipe = this.recipes.get(position);

            Intent intent = new Intent(this.context, RecipeDetailsActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            intent.putExtra("recipe_name", recipe.getRecipeName());
            this.context.startActivity(intent);
        }
    }
}
