package com.example.michaeldruyan.classscheduleait.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.R;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMain);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.ivMain);

        }
    }

    private Context context;
    private String[] titles;
    private String[] descriptions;

    public SectionAdapter (String[] title, String[] description, Context mContext){
        this.titles = title;
        descriptions = description;
        context = mContext;
    }

    /*
    @Override
    public Object getItem(int position) {
        return titles[position];
    }
    */

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tvDescription.setText(descriptions[position]);
        viewHolder.tvTitle.setText(titles[position]);

        if(titles[position].equalsIgnoreCase("Timetable")){
            viewHolder.imageView.setImageResource(R.drawable.timetable);
        }else if(titles[position].equalsIgnoreCase("Subjects")){
            viewHolder.imageView.setImageResource(R.drawable.book);
        }else if(titles[position].equalsIgnoreCase("Faculty")){
            viewHolder.imageView.setImageResource(R.drawable.contact);
        }else{
            viewHolder.imageView.setImageResource(R.drawable.settings);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
