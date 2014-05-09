package com.meshcron.alarm_app;


import com.example.alarm_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SetInBedTime extends Activity{
	private final String TAG = "SetInBedTime";
	private int inBedStartHour,inBedStartMin;
	private int inBedStartPointTotalMin;
	private int inBedEndHour,inBedEndMin;
	private int inBedEndPointTotalMin;
	private int inBedTotalMin,inBedTotalHour;
	private SeekBar seekInBedStartTime, seekInBedEndTime;
	private TextView textInBedStartTime, textInBedEndTime;
	private String inBedStartTimeString, inBedEndTimeString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setinbedtime);
		initUi();
		initData();
		seekInBedStartTime.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				inBedStartPointTotalMin = progress;	
				setStartTimeDisplay(inBedStartPointTotalMin);
			}
		});
		
		seekInBedEndTime.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				inBedEndPointTotalMin = progress;
				setEndTimeDisplay(inBedEndPointTotalMin);
			}
		});
		
	}
	
	private void initUi(){
		seekInBedStartTime = (SeekBar)findViewById(R.id.seek_inBedStartTime);
		seekInBedEndTime = (SeekBar)findViewById(R.id.seek_inBedEndTime);
		//Setting seek bar limit to max 1 day mins
		seekInBedStartTime.setMax(1440);
		seekInBedEndTime.setMax(1440);
		
		textInBedStartTime = (TextView)findViewById(R.id.textInBedStartTime);
		textInBedEndTime = (TextView)findViewById(R.id.txtInBedEndTime);
		
	}
	
	private void initData(){
		Intent i = getIntent();
		Bundle dataBundle = i.getExtras();
		if(dataBundle != null){
			inBedStartHour = dataBundle.getInt("start_hr", 11);
			inBedStartMin = dataBundle.getInt("start_min", 46);
			inBedTotalMin = dataBundle.getInt("prescribed_sleep_time_min");
			inBedTotalHour = dataBundle.getInt("prescribed_sleep_time_hour");
			inBedEndHour = inBedStartHour + inBedTotalHour;
			inBedEndMin = inBedStartMin + inBedTotalMin;

			if(inBedEndMin > 59){
				inBedEndHour = inBedEndHour + (inBedEndMin / 60);
				inBedEndMin = inBedEndMin % 60;
			}

		}else{
			//if we come from debug button 11:46 start time
			inBedStartHour = 11;
			inBedStartMin = 46;
			//Regarding prescribed sleep time which we calculate from Other_Note (5 hr and 30 min)
			inBedTotalMin = 30;
			inBedTotalHour = 5;
			inBedEndHour = inBedStartHour + inBedTotalHour;
			inBedEndMin = inBedStartMin + inBedTotalMin;
			
			if(inBedEndMin > 59){
				inBedEndHour = inBedEndHour + (inBedEndMin / 60);
				inBedEndMin = inBedEndMin % 60;
			}
			
		}
		inBedStartTimeString = String.format("%2d:%2d", inBedStartHour,inBedStartMin);
		Log.i(TAG,"inBedStartTimeString:- " + inBedStartTimeString);
		textInBedStartTime.setText(inBedStartTimeString);
		
		inBedEndTimeString = String.format("%2d:%2d", inBedEndHour,inBedEndMin);
		Log.i(TAG,"inBedStartTimeString:- " + inBedEndTimeString);
		textInBedEndTime.setText(inBedEndTimeString);
		
		inBedStartPointTotalMin = inBedStartMin + (inBedStartHour * 60);
		seekInBedStartTime.setProgress(inBedStartPointTotalMin);
		
		inBedEndPointTotalMin = inBedEndMin + (inBedEndHour * 60);
		seekInBedEndTime.setProgress(inBedEndPointTotalMin);
	}
	
	private void setStartTimeDisplay(int totalMin){
		inBedStartHour = totalMin/60;
		inBedStartMin = totalMin % 60;
		inBedStartTimeString = String.format("%02d:%02d", inBedStartHour,inBedStartMin);
		Log.i(TAG,"inBedStartTimeString:- " + inBedStartTimeString);
		textInBedStartTime.setText(inBedStartTimeString);
	}
	private void setEndTimeDisplay(int totalMin){
		inBedEndHour = totalMin/60;
		inBedEndMin = totalMin % 60;
		inBedEndTimeString = String.format("%02d:%02d", inBedEndHour,inBedEndMin);
		Log.i(TAG,"inBedStartTimeString:- " + inBedEndTimeString);
		textInBedEndTime.setText(inBedEndTimeString);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	

}
