package com.chana.recipesearch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CourseSearchAdapter extends RecyclerView.Adapter<CourseSearchAdapter.CourseSearchViewHolder>{

    private List<Recipe> recipes;
    Context context;

    public CourseSearchAdapter(List<Recipe> recipes, Context context){
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_layout, parent, false);
        CourseSearchAdapter.CourseSearchViewHolder courseSearchViewHolder =
                new CourseSearchAdapter.CourseSearchViewHolder(view, context, recipes);
        return courseSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseSearchAdapter.CourseSearchViewHolder holder, int position) {
            String recipe_image = recipes.get(position).getImage();
            Glide.with(context)
                    .load(recipe_image).into(holder.recipe_image);

        //holder.recipe_image.setImageDrawable(LoadImageFromWeb(recipe_image));
            holder.recipeName.setText( recipes.get(position).getRecipeName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static Drawable LoadImageFromWeb(String url) {
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(inputStream, "src name");
        } catch (Exception e) {
            return null;
        }
    }


    public static class  CourseSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recipe_image;
        TextView recipeName;
        List<Recipe> recipes;
        Context context;

        public CourseSearchViewHolder(@NonNull View itemView, Context context, List<Recipe> recipes) {
            super(itemView);
            this.recipes = recipes;
            this.context = context;
            itemView.setOnClickListener(this);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);

//        if (currentArticle != null) {
//            Picasso.with(this.getContext())
//                    .load(currentArticle.getmImageUrl())
//                    .centerCrop()
//                    .transform(new CircleTransform(50,0))
//                    .fit()
//                    .into(recipe);
// }
        }

        @Override
        public void onClick(View view) {

        }
    }
}
