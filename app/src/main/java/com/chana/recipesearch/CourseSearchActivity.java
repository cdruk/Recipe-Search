package com.chana.recipesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.Single;

public class CourseSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CourseSearchAdapter mCourseSearchAdapter;

    private RecipeClient client = new RecipeClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        recyclerView = findViewById(R.id.recipe_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Course course = (Course) getIntent().getSerializableExtra("course");

        Single<List<Recipe>> courseRecipes = client.getCourseRecipes(course);
        courseRecipes.subscribe()
//        mCourseSearchAdapter = new CourseSearchAdapter(courses, this);
//        recyclerView.setAdapter(mCourseSearchAdapter);
    }
}