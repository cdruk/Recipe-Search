package com.chana.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseSearchAdapter extends RecyclerView.Adapter<CourseSearchAdapter.CourseSearchViewHolder>{

    @NonNull
    @Override
    public CourseSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseSearchAdapter.CourseSearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class  CourseSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView course;
        TextView courseTitle;
        ArrayList<Course> courses;
        Context context;

        public CourseSearchViewHolder(@NonNull View itemView, Context context, ArrayList<Course> courses) {
            super(itemView);
            this.courses = courses;
            this.context = context;
            itemView.setOnClickListener(this);
            course = itemView.findViewById(R.id.course);
            courseTitle = itemView.findViewById(R.id.course_title);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
