package com.example.michaeldruyan.classscheduleait;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.adapter.SectionAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //private ListView listView;
    private SectionAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.ToolbarMain);
        //listView = findViewById(R.id.lvMain);
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

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }
                }
            }
        });
        */
    }

    public class SimpleAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private Context context;

        private TextView title, description;
        private String[] titles;
        private String[] descriptions;
        private ImageView imageView;

        public SimpleAdapter(Context mContext, String[] title, String[] description){
            context = mContext;
            titles = title;
            descriptions = description;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View myView, ViewGroup parent) {
            if(myView == null){
                myView = layoutInflater.inflate(R.layout.main_activity_item, null);
            }

            title = myView.findViewById(R.id.tvMain);
            description = myView.findViewById(R.id.tvDescription);
            imageView = myView.findViewById(R.id.ivMain);

            title.setText(titles[position]);
            description.setText(descriptions[position]);

            if(titles[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable);
            }else if(titles[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.book);
            }else if(titles[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.contact);
            }else{
                imageView.setImageResource(R.drawable.settings);
            }

            return myView;

        }
    }
}
