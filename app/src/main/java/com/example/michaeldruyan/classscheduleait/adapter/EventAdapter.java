package com.example.michaeldruyan.classscheduleait.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeldruyan.classscheduleait.CreateAndEditEventDialog;
import com.example.michaeldruyan.classscheduleait.R;
import com.example.michaeldruyan.classscheduleait.data.AppDatabase;
import com.example.michaeldruyan.classscheduleait.data.Event;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Patrick on 5/16/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvStartTime;
        public TextView tvEndTime;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvEventName);
            tvStartTime = (TextView) itemView.findViewById(R.id.tvEventStartTime);
            tvEndTime = (TextView) itemView.findViewById(R.id.tvEventEndTime);

        }
    }

    private List<Event> eventList;
    private Context context;
    private int lastPosition = -1;

    public EventAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;

        System.out.println("SIZE ADAPTER");
        System.out.println(eventList.size());

        Collections.sort(eventList, new TimeComparator());

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_row_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tvName.setText(eventList.get(position).getName());

        viewHolder.tvStartTime.setText(Integer.toString(eventList.get(position).getStartHour()) + ":"+
                Integer.toString(eventList.get(position).getStartMinute()));

        viewHolder.tvEndTime.setText(Integer.toString(eventList.get(position).getEndHour()) + ":"+
                Integer.toString(eventList.get(position).getEndMinute()));

//        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removePlace(viewHolder.getAdapterPosition());
//            }
//        });
//        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) context).showEditPlaceDialog(placesList.get(viewHolder.getAdapterPosition()));
//            }
//        });

        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void addEvent(Event event) {
        eventList.add(event);
        Collections.sort(eventList, new TimeComparator());
        notifyDataSetChanged();
        System.out.println("SIZE EVENT ADAPTER");
        System.out.println(eventList.size());
    }

    public void updateEvent(Event event) {
        int editPos = findEventIndexByEventId(event.getEventId());
        eventList.set(editPos, event);
        notifyItemChanged(editPos);
    }

    private int findEventIndexByEventId(long placeId) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getEventId() == placeId) {
                return i;
            }
        }

        return -1;
    }

    public void removeEvent(int position) {
        final Event placeToDelete = eventList.get(position);
        eventList.remove(placeToDelete);
        notifyItemRemoved(position);
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getAppDatabase(context).eventDao().delete(
                        placeToDelete);
            }
        }.start();
    }

//    public void swapPlaces(int oldPosition, int newPosition) {
//        if (oldPosition < newPosition) {
//            for (int i = oldPosition; i < newPosition; i++) {
//                Collections.swap(placesList, i, i + 1);
//            }
//        } else {
//            for (int i = oldPosition; i > newPosition; i--) {
//                Collections.swap(placesList, i, i - 1);
//            }
//        }
//        notifyItemMoved(oldPosition, newPosition);
//    }

    public Event getPlace(int i) {
        return eventList.get(i);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class TimeComparator implements Comparator<Event> {

        @Override
        public int compare(Event o1, Event o2){
            if(o1.startHour < o2.startHour){
                return -1;
            } else if (o1.startHour < o2.startHour) {
                return 1;
            } else {
                if(o1.startMinute < o2.startMinute){
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
