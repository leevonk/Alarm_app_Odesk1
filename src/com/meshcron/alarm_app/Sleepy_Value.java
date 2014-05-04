package com.meshcron.alarm_app;

import android.R.string;
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
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.alarm_app.R;

public class Sleepy_Value extends Activity {
	SeekBar Sleep_value;
	Button Continue;
	int progress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sleepy_value);
		Sleep_value=(SeekBar)findViewById(R.id.seek_sleep);
		Continue=(Button)findViewById(R.id.btn_cont);
		Continue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 SharedPreferences preferences = getSharedPreferences("sleep_value", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("sleep",progress);
					editor.commit();
					Log.e("sleep",""+progress);
					Intent slp=new Intent(Sleepy_Value.this,Lay_Down.class);
					startActivity(slp);
			}
		});
		Sleep_value.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
        {
          	
          	@Override
          	public void onProgressChanged(SeekBar seekBar, int progressValue ,boolean fromUser)
          	{
          		progress = progressValue;
          	}
          	@Override
          	public void onStartTrackingTouch(SeekBar seekBar) 	{      	}
          	@Override
          	public void onStopTrackingTouch(SeekBar seekBar)   	{      	}
        });
	}
	
}
		