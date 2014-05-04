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

import com.example.alarm_app.R;

public class Out_bed extends Activity {
Button conti;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_out_bed);
		conti=(Button)findViewById(R.id.cont_out);
		conti.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Date dt = new Date();
		         int hours = dt.getHours();
		         int minutes = dt.getMinutes();
		         SharedPreferences preferences = getSharedPreferences("out_bed", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("hour",hours);
					editor.putInt("minutes",minutes);
					editor.commit();
					Intent out_bed=new Intent(Out_bed.this,Sleep_well.class);
					startActivity(out_bed);
			}
		});
	}
}
