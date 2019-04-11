package com.chana.recipesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CourseAdapter mCourseAdapter;

    private int[] courseImages = {R.drawable.appetizer, R.drawable.salad, R.drawable.soup,
            R.drawable.side, R.drawable.main, R.drawable.dessert};
    private String[] courseTitles = {"Appetizers", "Salads", "Soups", "Side Dishes",
            "Main Dishes", "Desserts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.course_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mCourseAdapter = new CourseAdapter(courseImages, courseTitles);
        recyclerView.setAdapter(mCourseAdapter);

    }
}
