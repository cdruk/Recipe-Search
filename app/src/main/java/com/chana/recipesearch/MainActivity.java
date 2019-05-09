package com.chana.recipesearch;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CourseAdapter mCourseAdapter;
    private FloatingActionButton fab;
    private ArrayList<Course> courses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.course_view);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        setupFab();
        setupCourses();

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mCourseAdapter = new CourseAdapter(courses, this);
        recyclerView.setAdapter(mCourseAdapter);

    }

    private void setupFab() {
        fab = findViewById(R.id.open_recipe_search);
        fab.setOnClickListener(view -> startActivity(new Intent(view.getContext(), RecipeSearchActivity.class)));
    }

    private void setupCourses() {
        courses.add(Course.APPETIZERS);
        courses.add(Course.SALADS);
        courses.add(Course.SOUPS);
        courses.add(Course.SIDES);
        courses.add(Course.MAINS);
        courses.add(Course.DESSERTS);
    }
}
