package com.example.michaeldruyan.classscheduleait;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.michaeldruyan.classscheduleait.adapter.EventAdapter;
import com.example.michaeldruyan.classscheduleait.adapter.SectionAdapter;
import com.example.michaeldruyan.classscheduleait.data.AppDatabase;
import com.example.michaeldruyan.classscheduleait.data.Event;

import java.util.List;

public class DayActivity extends AppCompatActivity {

    private List<Event> eventList;
    private Toolbar toolbar;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        toolbar = findViewById(R.id.toolbar_day);
        initToolbar();

        RecyclerView recyclerViewList = findViewById(R.id.recyclerViewDayList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));

        initEvents(recyclerViewList);

    }

    private void initToolbar(){
        String day = getIntent().getStringExtra("DAY");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(day);
    }

    private void initEvents(final RecyclerView recyclerView) {



        new Thread(){
            @Override
            public void run() {
                String day = getIntent().getStringExtra("DAY");
                final List<Event> events =
                        AppDatabase.getAppDatabase(DayActivity.this).eventDao().getDayEvents(day);

                System.out.println("SIZE");
                System.out.println(events.size());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        eventAdapter = new EventAdapter(events, DayActivity.this);
                        recyclerView.setAdapter(eventAdapter);

//                        PlacesListTouchHelperCallback touchHelperCallback = new PlacesListTouchHelperCallback(
//                                placesAdapter);
//                        ItemTouchHelper touchHelper = new ItemTouchHelper(
//                                touchHelperCallback);
//                        touchHelper.attachToRecyclerView(recyclerView);
                    }
                });
            }
        }.start();
    }



}
