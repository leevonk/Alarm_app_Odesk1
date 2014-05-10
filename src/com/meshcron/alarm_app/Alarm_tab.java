package com.meshcron.alarm_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.example.alarm_app.R;

public class Alarm_tab extends Fragment {
	private final String TAG = "Alarm_tab";
	private TimePicker timePicker;
	private int hour,min;
	private Button btnSave;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
		timePicker = (TimePicker)rootView.findViewById(R.id.timePickers);
		btnSave = (Button)rootView.findViewById(R.id.saveAlarm);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Need to save this time like normal alarm
				SharedPreferences sp = getActivity().getSharedPreferences("wakeup_alarm", Context.MODE_PRIVATE);
				Editor edit = sp.edit();
				edit.putInt("alarm_hour", hour);
				edit.putInt("alarm_min",min);
				edit.commit();
				Toast.makeText(getActivity(), "Alarm Set:- " + hour + ":" + min, Toast.LENGTH_SHORT).show();
			}
		});
		hour = timePicker.getCurrentHour();
		min = timePicker.getCurrentMinute();
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				hour = hourOfDay;
				min = minute;
				Log.i(TAG,"hour:- " + hour);
				Log.i(TAG,"minute:- " + min);
			}
		});
		return rootView;
	}
}