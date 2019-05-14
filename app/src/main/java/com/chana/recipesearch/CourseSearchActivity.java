package com.chana.recipesearch;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CourseSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeClient client = new RecipeClient();
    private CourseSearchAdapter mCourseSearchAdapter;

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

        mCourseSearchAdapter = new CourseSearchAdapter(this);
        recyclerView.setAdapter(mCourseSearchAdapter);

        client.getCourseRecipes(course, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setCourseRecipes, this::onError);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar(String courseName) {
       @NonNull ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(courseName);
    }

    private void setCourseRecipes(List<Recipe> list) {
        mCourseSearchAdapter.getRecipes().addAll(list);
        mCourseSearchAdapter.notifyDataSetChanged();

    }


    private void onError(Throwable t) {
        t.printStackTrace();
    }
}
