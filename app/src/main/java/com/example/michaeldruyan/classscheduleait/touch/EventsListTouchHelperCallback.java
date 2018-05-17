package com.example.michaeldruyan.classscheduleait.touch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.michaeldruyan.classscheduleait.adapter.EventAdapter;

public class EventsListTouchHelperCallback extends ItemTouchHelper.Callback{
    private EventAdapter adapter;

    public EventsListTouchHelperCallback(EventAdapter adapter) {
        this.adapter = adapter;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeEvent(viewHolder.getAdapterPosition());
    }
}
