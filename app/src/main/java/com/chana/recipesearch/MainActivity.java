package com.chana.recipesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CourseAdapter mCourseAdapter;

//    private int[] courseImages = {R.drawable.appetizer, R.drawable.salad, R.drawable.soup,
//            R.drawable.side, R.drawable.main, R.drawable.dessert};
//    private String[] courseTitles = {"Appetizers", "Salads", "Soups", "Side Dishes",
//            "Main Dishes", "Desserts"};

    private ArrayList<Course> courses = new ArrayList<Course>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.course_view);

        courses.add(Course.APPETIZERS);
        courses.add(Course.SALADS);
        courses.add(Course.SOUPS);
        courses.add(Course.SIDES);
        courses.add(Course.MAINS);
        courses.add(Course.DESSERTS);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

//        mCourseAdapter = new CourseAdapter(courseImages, courseTitles, this);
        mCourseAdapter = new CourseAdapter(courses, this);
        recyclerView.setAdapter(mCourseAdapter);

    }
}
