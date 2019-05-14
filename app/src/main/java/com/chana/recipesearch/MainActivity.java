package com.chana.recipesearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        setupActionBar();

        setupFab();
        setupCourses();

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mCourseAdapter = new CourseAdapter(courses, this);
        recyclerView.setAdapter(mCourseAdapter);

    }

    private void setupActionBar() {
        @NonNull ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.course_explorer);
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
