package com.chana.recipesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        client.getCourseRecipes(course)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::setCourseRecipes, ::onError)
//        mCourseSearchAdapter = new CourseSearchAdapter(courses, this);
//        recyclerView.setAdapter(mCourseSearchAdapter);
    }
    
    private void setCourseRecipes(List<Recipe> list) {
        // display the list of recipes
    }
    
    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
