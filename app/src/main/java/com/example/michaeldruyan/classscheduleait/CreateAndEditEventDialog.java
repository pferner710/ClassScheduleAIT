package com.example.michaeldruyan.classscheduleait;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.michaeldruyan.classscheduleait.data.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 5/16/18.
 */

public class CreateAndEditEventDialog extends DialogFragment{

    public interface EventHandler {
        public void onNewEventCreated(Event event);

        public void onEventUpdated(Event event);
    }


    private EventHandler eventHandler;
    private EditText etName;
    private CheckBox cbMonday;
    private CheckBox cbTuesday;
    private CheckBox cbWednesday;
    private CheckBox cbThursday;
    private CheckBox cbFriday;
    private CheckBox cbSaturday;
    private CheckBox cbSunday;
    private EditText etStartHour;
    private EditText etStartMinute;
    private EditText etEndHour;
    private EditText etEndMinute;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EventHandler) {
            eventHandler = (EventHandler)context;
        } else {
            throw new RuntimeException(
                    "Wrong Interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Event");

        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_create_event, null);

        etName = rootView.findViewById(R.id.etEventName);
        cbMonday = rootView.findViewById(R.id.cbMonday);
        cbTuesday = rootView.findViewById(R.id.cbTuesday);
        cbWednesday = rootView.findViewById(R.id.cbWednesday);
        cbThursday = rootView.findViewById(R.id.cbThursday);
        cbFriday = rootView.findViewById(R.id.cbFriday);
        cbSaturday = rootView.findViewById(R.id.cbSaturday);
        cbSunday = rootView.findViewById(R.id.cbSunday);
        etStartHour = rootView.findViewById(R.id.etStartHour);
        etStartMinute = rootView.findViewById(R.id.etStartMinute);
        etEndHour = rootView.findViewById(R.id.etEndHour);
        etEndMinute = rootView.findViewById(R.id.etEndMinute);

        builder.setView(rootView);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(etName.getText())) {


                        List<String> dayList = new ArrayList<String>();
                        if (cbMonday.isChecked()) {
                            dayList.add("Monday");
                        } else if (cbTuesday.isChecked()) {
                            dayList.add("Tuesday");
                        } else if (cbWednesday.isChecked()) {
                            dayList.add("Wednesday");
                        } else if (cbThursday.isChecked()) {
                            dayList.add("Thursday");
                        } else if (cbFriday.isChecked()) {
                            dayList.add("Friday");
                        } else if (cbSaturday.isChecked()) {
                            dayList.add("Saturday");
                        } else if (cbSunday.isChecked()) {
                            dayList.add("Sunday");
                        }

                        for (String day : dayList) {

                            if (getArguments() != null &&
                                    getArguments().containsKey(MainActivity.KEY_EDIT)) {

                                Event eventToEdit = (Event) getArguments().getSerializable(MainActivity.KEY_EDIT);
                                eventToEdit.setName(etName.getText().toString());
                                eventToEdit.setDay(day);
                                eventToEdit.setStartHour(Integer.parseInt(etStartHour.getText().toString()));
                                eventToEdit.setEndHour(Integer.parseInt(etEndHour.getText().toString()));
                                eventToEdit.setStartMinute(Integer.parseInt(etStartMinute.getText().toString()));
                                eventToEdit.setEndMinute(Integer.parseInt(etEndMinute.getText().toString()));

                            } else {
                                Event event = new Event(
                                        etName.getText().toString(),
                                        Integer.parseInt(etStartHour.getText().toString()),
                                        Integer.parseInt(etStartHour.getText().toString()),
                                        Integer.parseInt(etStartHour.getText().toString()),
                                        Integer.parseInt(etStartHour.getText().toString()),
                                        day
                                );
                                eventHandler.onNewEventCreated(event);
                            }
                        }

                        d.dismiss();

                    } else {
                        etName.setError("Cannot be empty");
                    }
                }
            });
        }
    }
}
