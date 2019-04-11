package com.chana.recipesearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private int [] courseImages;
    private String [] courseTitles;

    public CourseAdapter(int [] courseImages, String [] courseTitles){
        this.courseImages = courseImages;
        this.courseTitles = courseTitles;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_layout, parent, false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        int courseImage_id = courseImages[position];
        holder.course.setImageResource(courseImage_id);
        holder.courseTitle.setText(courseTitles[position]);
    }

    @Override
    public int getItemCount() {
        return courseImages.length;
    }

    public static class  CourseViewHolder extends RecyclerView.ViewHolder{
        ImageView course;
        TextView courseTitle;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            courseTitle = itemView.findViewById(R.id.course_title);
        }
    }
}