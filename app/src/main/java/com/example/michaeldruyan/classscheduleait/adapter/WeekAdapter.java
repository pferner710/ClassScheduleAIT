package com.example.michaeldruyan.classscheduleait.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.R;
import com.example.michaeldruyan.classscheduleait.WeekActivity;

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
            viewHolder.ivWeekLogo.setImageResource(R.drawable.monday);
        } else if (weeks[position].equalsIgnoreCase("Tuesday")) {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.tuesday);
        } else if (weeks[position].equalsIgnoreCase("Wednesday")) {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.wednesday);
        } else if (weeks[position].equalsIgnoreCase("Thursday")) {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.thursday);
        } else if (weeks[position].equalsIgnoreCase("Friday")) {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.friday);
        } else if (weeks[position].equalsIgnoreCase("Saturday")) {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.saturday);
        } else {
            viewHolder.ivWeekLogo.setImageResource(R.drawable.sunday);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (weeks[position].equalsIgnoreCase("Monday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Monday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Tuesday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Tuesday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Wednesday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Wednesday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Thursday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Thursday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Friday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Friday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Saturday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Saturday");
//                    (context).startActivity(dayLaunch);
//                } else if (weeks[position].equalsIgnoreCase("Sunday")) {
//                    Intent dayLaunch = new Intent(context, DayActivity.class);
//                    dayLaunch.putExtra("DAY", "Sunday");
//                    (context).startActivity(dayLaunch);
//                }
            }

        });
    }
}
