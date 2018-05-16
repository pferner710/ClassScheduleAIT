package com.example.michaeldruyan.classscheduleait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.michaeldruyan.classscheduleait.adapter.SectionAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static final String KEY_EDIT = "KEY_EDIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.ToolbarMain);
        initToolbar();

        RecyclerView recyclerViewList = findViewById(R.id.recyclerViewList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));



        setupView(recyclerViewList);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AIT Scheduler");
    }

    private void setupView(RecyclerView recyclerView){

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SectionAdapter simpleAdapter = new SectionAdapter(title,description, this);
        recyclerView.setAdapter(simpleAdapter);

    }
}
