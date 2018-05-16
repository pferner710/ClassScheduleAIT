package com.example.michaeldruyan.classscheduleait;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.LetterImageView;

import org.w3c.dom.Text;

public class WeekActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        toolbar = findViewById(R.id.ToolbarWeek);

        //initialize toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setupUIViews();

        RecyclerView recyclerViewWeekList = findViewById(R.id.recyclerViewWeekList);
        recyclerViewWeekList.setLayoutManager(new LinearLayoutManager(this));



        setupView(recyclerViewWeekList);
    }

    private void setupUIViews() {
        //sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE);
    }


    private void setupView(RecyclerView recyclerView) {
        String[] week = getResources().getStringArray(R.array.DaysOfWeek);

        //WeekAdapter adapter = new WeekAdapter(this, R.layout.week_row_item, week);
        WeekAdapter adapter = new WeekAdapter(this, week);

        //WeekAdapter simpleAdapter = new WeekAdapter(title,description, this);
        recyclerView.setAdapter(simpleAdapter);

        /*
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    default:
                        break;
                }
            }
        });

    */
    }

    public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivWeekLogo;
            private TextView tvWeekName;

            public ViewHolder(View itemView) {
                super(itemView);
                ivWeekLogo = itemView.findViewById(R.id.ivWeekLogo);
                tvWeekName = itemView.findViewById(R.id.tvWeekName);
            }
        }

        private Context context;
        private String[] weeks;

        public WeekAdapter(Context context, String[] daysOfWeek) {
            this.context = context;
            //this.resource = resource;
            this.weeks = daysOfWeek;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.week_row_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public int getItemCount() {
            return weeks.length;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
            viewHolder.tvWeekName.setText(weeks[position]);

            if (weeks[position].equalsIgnoreCase("Monday")) {
                viewHolder.imageView.setImageResource(R.drawable.timetable);
            } else if (weeks[position].equalsIgnoreCase("Tuesday")) {
                viewHolder.imageView.setImageResource(R.drawable.book);
            } else if (weeks[position].equalsIgnoreCase("Wednesday")) {
                viewHolder.imageView.setImageResource(R.drawable.contact);
            } else if (weeks[position].equalsIgnoreCase("Thursday")) {
                viewHolder.imageView.setImageResource(R.drawable.contact);
            } else if (weeks[position].equalsIgnoreCase("Friday")) {
                viewHolder.imageView.setImageResource(R.drawable.contact);
            } else if (weeks[position].equalsIgnoreCase("Saturday")) {
                viewHolder.imageView.setImageResource(R.drawable.contact);
            } else {
                viewHolder.imageView.setImageResource(R.drawable.contact);
            }
        }
    }
}


/*
        private int resource;
        private LayoutInflater layoutInflater;

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetter);
                holder.tvWeek = (TextView)convertView.findViewById(R.id.tvMain);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(week[position].charAt(0));
            holder.tvWeek.setText(week[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvWeek;
        }
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
*/
