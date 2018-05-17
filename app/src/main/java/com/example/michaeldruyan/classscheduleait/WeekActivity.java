package com.example.michaeldruyan.classscheduleait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.michaeldruyan.classscheduleait.adapter.WeekAdapter;

public class WeekActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        toolbar = findViewById(R.id.ToolbarWeek);

        //initialize toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.week);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView recyclerViewWeekList = findViewById(R.id.recyclerViewWeekList);
        recyclerViewWeekList.setLayoutManager(new LinearLayoutManager(this));


        setupView(recyclerViewWeekList);
    }


    private void setupView(RecyclerView recyclerView) {
        String[] week = getResources().getStringArray(R.array.DaysOfWeek);

        WeekAdapter adapter = new WeekAdapter(this, week);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}



