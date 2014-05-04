package com.meshcron.alarm_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.alarm_app.R;

public class Sleep_well extends Activity {

SeekBar sleep;
Button cont_slp;
int progress=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sleep_well);
		sleep=(SeekBar)findViewById(R.id.seek_sleep);
		cont_slp=(Button)findViewById(R.id.cont_sleep);
		cont_slp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 SharedPreferences preferences = getSharedPreferences("sleep_well", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("sleep",progress);
					editor.commit();
					Log.e("sleep",""+progress);
					Intent slp=new Intent(Sleep_well.this,Rel_tags.class);
					startActivity(slp);
			}
		});
		sleep.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
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