package com.meshcron.alarm_app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alarm_app.R;

public class Other_notes extends Activity {
	private final String TAG = "Other_notes";
	Button cont_other;
	EditText notes;
	String dayOfTheWeek;
	int count=1;
	int today;
	private int prescribed_sleep_time_min, prescribed_sleep_time_hour, get_con;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_other_notes);
		cont_other=(Button)findViewById(R.id.cont_notes);
		notes=(EditText)findViewById(R.id.edt_notes);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE",Locale.US);
		Date d = new Date();
		SharedPreferences get_coun = getSharedPreferences("count", Context.MODE_PRIVATE);
        get_con = get_coun.getInt("count",0);
        if(get_con>7){
        	count=1;
        	SharedPreferences day1 = getSharedPreferences("monday", Context.MODE_PRIVATE);
	         int d1_hours= day1.getInt("hours",0);
	         int d1_mins=day1.getInt("mins",0);
	         SharedPreferences day2 = getSharedPreferences("tuesday", Context.MODE_PRIVATE);
	         int d2_hours= day2.getInt("hours",0);
	         int d2_mins=day2.getInt("mins",0);
	         SharedPreferences day3 = getSharedPreferences("wednesday", Context.MODE_PRIVATE);
	         int d3_hours= day3.getInt("hours",0);
	         int d3_mins=day3.getInt("mins",0);
	         SharedPreferences day4 = getSharedPreferences("thursday", Context.MODE_PRIVATE);
	         int d4_hours= day4.getInt("hours",0);
	         int d4_mins=day4.getInt("mins",0);
	         SharedPreferences day5 = getSharedPreferences("friday", Context.MODE_PRIVATE);
	         int d5_hours= day5.getInt("hours",0);
	         int d5_mins=day5.getInt("mins",0);
	         SharedPreferences day6 = getSharedPreferences("saturday", Context.MODE_PRIVATE);
	         int d6_hours= day6.getInt("hours",0);
	         int d6_mins=day6.getInt("mins",0);
	         SharedPreferences day7 = getSharedPreferences("sunday", Context.MODE_PRIVATE);
	         int d7_hours= day7.getInt("hours",0);
	         int d7_mins=day7.getInt("mins",0);
	         
	         int average_sleep_hours=(d1_hours + d2_hours + d3_hours + d4_hours + d5_hours + d6_hours + d7_hours)/7;
	         Log.i("TAG","average_sleep_hour:- " + average_sleep_hours);
	         
	         int average_sleep_mins= (d1_mins + d2_mins + d3_mins + d4_mins + d5_mins + d6_mins + d7_mins)/7;
	         Log.i(TAG, "average_sleep_min:- " + average_sleep_mins);
	         
	         prescribed_sleep_time_min = average_sleep_mins + 30;
	         prescribed_sleep_time_hour = average_sleep_hours;
	         if(prescribed_sleep_time_min > 59){
	        	 prescribed_sleep_time_hour = prescribed_sleep_time_hour + (prescribed_sleep_time_min / 60);
	        	 prescribed_sleep_time_min = prescribed_sleep_time_min % 60;
	         }
	         Log.i(TAG, "prescribed_sleep_time:- " + prescribed_sleep_time_hour + " : " + prescribed_sleep_time_min);
	         
	         
        }
	    dayOfTheWeek = sdf.format(d);
		Log.e("day",dayOfTheWeek);
		cont_other.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences preferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				editor.putString("notes",notes.getText().toString());
				editor.commit();
				SharedPreferences s4 = getSharedPreferences("lay_time", Context.MODE_PRIVATE);
		         int s4_hours= s4.getInt("hour",0);
		         int s4_mins=s4.getInt("minutes",0);
		         Log.e("s4 time",""+s4_hours+s4_mins);
		         SharedPreferences s6 = getSharedPreferences("get_out", Context.MODE_PRIVATE);
		         int s6_hours= s6.getInt("hour",0);
		         int s6_mins=s6.getInt("minutes",0);
		         Log.e("s6 time",""+s6_hours+s6_mins);
		         SharedPreferences s7 = getSharedPreferences("awake", Context.MODE_PRIVATE);
		         int s7_hours= s7.getInt("hour",0);
		         int s7_mins=s7.getInt("minutes",0);
		         Log.e("s7 time",""+s7_hours+s7_mins);
		         int hours47=s7_hours-s4_hours;
		         Log.e("s47 hours",""+hours47);
		         int mins47=s7_mins-s4_mins;
		         Log.e("s44 mins",""+mins47);
		         int hours46=s6_hours-s4_hours;
		         Log.e("s46 hours",""+hours46);
		         int mins46=s6_mins-s4_mins;
		         Log.e("s46 mins",""+mins46);
		         int tot_hours=hours47-hours46;
		         Log.e("tot_hours",""+tot_hours);
		         int tot_mins=mins47-mins46;
		         Log.e("tot_mins",""+tot_mins);
				 Toast.makeText(Other_notes.this,
			               String.valueOf(tot_hours)+":"+String.valueOf(tot_mins) , Toast.LENGTH_SHORT).show();
				 notes.setText("");
				 if(dayOfTheWeek.equals("Monday")){
					 SharedPreferences mon = getSharedPreferences("monday", Context.MODE_PRIVATE);
						Editor editor_m = mon.edit();
						editor_m.putInt("hours",tot_hours);
						editor_m.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Tuesday")){
					 SharedPreferences tues = getSharedPreferences("tuesday", Context.MODE_PRIVATE);
						Editor editor_tu = tues.edit();
						editor_tu.putInt("hours",tot_hours);
						editor_tu.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Wednesday")){
					 SharedPreferences wed = getSharedPreferences("Wednesday", Context.MODE_PRIVATE);
						Editor editor_wed = wed.edit();
						editor_wed.putInt("hours",tot_hours);
						editor_wed.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Thursday")){
					 SharedPreferences thu = getSharedPreferences("thursday", Context.MODE_PRIVATE);
						Editor editor_thu = thu.edit();
						editor_thu.putInt("hours",tot_hours);
						editor_thu.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Friday")){
					 SharedPreferences fri = getSharedPreferences("friday", Context.MODE_PRIVATE);
						Editor editor_fri = fri.edit();
						editor_fri.putInt("hours",tot_hours);
						editor_fri.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Saturday")){
					 SharedPreferences sat = getSharedPreferences("Saturday", Context.MODE_PRIVATE);
						Editor editor_sat = sat.edit();
						editor_sat.putInt("hours",tot_hours);
						editor_sat.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 else if(dayOfTheWeek.equals("Sunday")){
					 SharedPreferences sun = getSharedPreferences("Sunday", Context.MODE_PRIVATE);
						Editor editor_sun = sun.edit();
						editor_sun.putInt("hours",tot_hours);
						editor_sun.putInt("mins",tot_mins);
						editor.commit();
						count++;
				 }
				 SharedPreferences coun = getSharedPreferences("count", Context.MODE_PRIVATE);
					Editor editor_coun = coun.edit();
					editor_coun.putInt("count",count);
					editor.commit();
					
				//If its finish of one week need to show set In Bed time activity on continue 	
				if(get_con>7){
					startSetInBedTimeAcitvity();
				}else{
					//Or Jump to main activity
					Intent i = new Intent(Other_notes.this, MainActivity.class);
					i.putExtra("Goto", "Clock_tab");
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			}
		});
	}
	/*
	 * Starting screen to show In Bed time and to adjust it.
	 */
	private void startSetInBedTimeAcitvity(){
		Intent i = new Intent(Other_notes.this, SetInBedTime.class);
        SharedPreferences sp = getSharedPreferences("lay_time", Context.MODE_PRIVATE);
        int start_hr = sp.getInt("hour", 0);
        int start_min = sp.getInt("min", 0);
        Bundle b = new Bundle();
        b.putInt("start_hr", start_hr);
        b.putInt("start_min", start_min);
        b.putInt("prescribed_sleep_time_hour", prescribed_sleep_time_hour);
        b.putInt("prescribed_sleep_time_min", prescribed_sleep_time_min);
        i.putExtra("data", b);
        startActivity(i);
	}
}