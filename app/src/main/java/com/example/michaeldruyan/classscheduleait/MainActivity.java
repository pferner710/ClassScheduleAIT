package com.example.michaeldruyan.classscheduleait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.michaeldruyan.classscheduleait.adapter.EventAdapter;
import com.example.michaeldruyan.classscheduleait.adapter.SectionAdapter;
import com.example.michaeldruyan.classscheduleait.data.AppDatabase;
import com.example.michaeldruyan.classscheduleait.data.Event;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CreateAndEditEventDialog.EventHandler{

    private Toolbar toolbar;
    private EventAdapter eventAdapter;
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

    private void showCreateEventDialog(){
        new CreateAndEditEventDialog().show(getSupportFragmentManager(), getString(R.string.createandeditdialog));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.actionAdd:
                showCreateEventDialog();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onNewEventCreated(final Event event){
        new Thread() {
            @Override
            public void run() {

                String day = event.day;
                final List<Event> events =
                        AppDatabase.getAppDatabase(MainActivity.this).eventDao().getDayEvents(day);


                boolean createEvent = true;
                for (Event eventInList : events) {
                    if(event.startHour < eventInList.endHour){
                        if(event.endHour >= eventInList.startHour){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, R.string.causes_schedule_conflict, Toast.LENGTH_LONG).show();
                                }
                            });
                            createEvent = false;
                        }
                    } else if (event.startHour == eventInList.endHour) {
                        if(event.startMinute < eventInList.endMinute) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, R.string.causes_schedule_conflict, Toast.LENGTH_LONG).show();
                                }
                            });                         createEvent = false;
                        }
                    }

                    if(event.startHour > event.endHour){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, R.string.start_time_after_end_time, Toast.LENGTH_LONG).show();
                            }
                        });                        createEvent = false;
                    } else if(event.startHour == event.endHour && event.startMinute > event.endMinute){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, R.string.start_time_after_end_time, Toast.LENGTH_LONG).show();
                            }
                        });                        createEvent = false;
                    }
                }

                if(event.startHour > 24 || event.startHour < 0 ||event.endHour > 24 || event.endHour < 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, R.string.invalid_time, Toast.LENGTH_LONG).show();
                        }
                    });
                }
                if(event.startMinute > 60 || event.startMinute < 0 ||event.endMinute > 60 || event.endMinute < 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, R.string.invalid_time, Toast.LENGTH_LONG).show();
                        }
                    });
                }


                if(createEvent){
                    long id = AppDatabase.getAppDatabase(MainActivity.this).
                            eventDao().insertEvent(event);
                    event.setEventId(id);
                }

            }
        }.start();
    }

    @Override
    public void onEventUpdated(final Event event) {
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getAppDatabase(MainActivity.this).eventDao().update(event);
            }
        }.start();
    }

}
