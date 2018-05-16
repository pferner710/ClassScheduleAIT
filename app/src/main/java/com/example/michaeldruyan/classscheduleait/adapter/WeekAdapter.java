package com.example.michaeldruyan.classscheduleait.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.R;

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
    }
}
