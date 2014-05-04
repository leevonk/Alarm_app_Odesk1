package com.meshcron.alarm_app;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.alarm_app.R;

public class Get_out extends Activity {

	RelativeLayout get;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_get_out);
		get=(RelativeLayout)findViewById(R.id.rel_get_out);
		get.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				 Date dt = new Date();
		         int hours = dt.getHours();
		         int minutes = dt.getMinutes();
		         SharedPreferences preferences = getSharedPreferences("get_out", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("hour",hours);
					editor.putInt("minutes",minutes);
					editor.commit();
					Intent get_out=new Intent(Get_out.this,Sleeping_time.class);
					startActivity(get_out);
				return false;
			}
		});
	}
}