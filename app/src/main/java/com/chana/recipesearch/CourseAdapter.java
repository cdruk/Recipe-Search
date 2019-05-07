package com.chana.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<Course> courses;
    Context context;

    public CourseAdapter(ArrayList<Course> courses, Context context){
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_layout, parent, false);
        CourseViewHolder courseViewHolder = new CourseViewHolder(view, context, courses);
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        int courseImage_id = courses.get(position).getCourseImage();
        holder.course_image.setImageResource(courseImage_id);
        holder.courseTitle.setText( courses.get(position).getSearchCriteria());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class  CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView course_image;
        TextView courseTitle;
//        int [] courseImages;
//        String [] courseTitles;
        ArrayList<Course> courses;
        Context context;

        public CourseViewHolder(@NonNull View itemView, Context context, ArrayList<Course> courses) {
            super(itemView);
//            this.courseImages = courseImages;
//            this.courseTitles = courseTitles;
            this.courses = courses;
            this.context = context;
            itemView.setOnClickListener(this);
            course_image = itemView.findViewById(R.id.course_image);
            courseTitle = itemView.findViewById(R.id.course_title);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Course course = this.courses.get(position);

            Intent intent = new Intent(this.context, CourseSearchActivity.class);
            intent.putExtra("course_image", course);
            this.context.startActivity(intent);
        }
    }
}