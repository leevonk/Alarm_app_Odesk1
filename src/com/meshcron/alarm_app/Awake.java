package com.meshcron.alarm_app;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.alarm_app.R;

public class Awake extends Activity {
	Button cont_awake;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_awake);
		Intent in=getIntent();
		Float light=in.getFloatExtra("light",0);		
		Float accel=in.getFloatExtra("accel",0);
		Toast.makeText(getApplicationContext(),"Accel="+accel+"light="+light,Toast.LENGTH_LONG).show();
		cont_awake=(Button)findViewById(R.id.btn_conti);
		cont_awake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Date dt = new Date();
		         int hours = dt.getHours();
		         int minutes = dt.getMinutes();
		         SharedPreferences preferences = getSharedPreferences("awake", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("hour",hours);
					editor.putInt("minutes",minutes);
					editor.commit();
		         SharedPreferences prefs = getSharedPreferences("lay_time", Context.MODE_PRIVATE);
		         int lay_hours= prefs.getInt("hour",0);
		         int lay_mins=prefs.getInt("minutes",0);
		         int tot_hours=hours-lay_hours;
		         int tot_mins=minutes-lay_mins;
		         int tot_minutes=tot_hours*60+tot_mins;
		         if(tot_minutes>30){
		        	 Intent more=new Intent(Awake.this,Get_out.class);
		        	 startActivity(more);
		         }
		         else{
		        	 Intent less=new Intent(Awake.this,Good_morning.class);
		        	 startActivity(less);
		         }
			}
		});
			}
}