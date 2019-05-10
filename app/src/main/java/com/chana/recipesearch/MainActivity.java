package com.chana.recipesearch;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

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
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        title.setText(R.string.course_explorer);
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
