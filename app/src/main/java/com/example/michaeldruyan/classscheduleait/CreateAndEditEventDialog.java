package com.example.michaeldruyan.classscheduleait;

import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.example.michaeldruyan.classscheduleait.data.Event;

/**
 * Created by Patrick on 5/16/18.
 */

public class CreateAndEditEventDialog extends DialogFragment {
    public interface ItemHandler {
        public void onNewItemCreated(Event event);

        public void onItemUpdated(Event event);
    }

    private ItemHandler itemHandler;
    private EditText
}
