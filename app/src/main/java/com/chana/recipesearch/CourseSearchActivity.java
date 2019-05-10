package com.chana.recipesearch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CourseSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecipeClient client = new RecipeClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        recyclerView = findViewById(R.id.recipe_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Course course = (Course) getIntent().getSerializableExtra("course");

        setupActionBar(course.getSearchCriteria());


        client.getCourseRecipes(course)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setCourseRecipes, this::onError);
    }

    private void setupActionBar(String courseName) {
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        title.setText(courseName);
    }

    private void setCourseRecipes(List<Recipe> list) {
        CourseSearchAdapter mCourseSearchAdapter = new CourseSearchAdapter(list, this);
        recyclerView.setAdapter(mCourseSearchAdapter);
    }
    
    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
