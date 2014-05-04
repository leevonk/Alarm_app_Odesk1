package com.meshcron.alarm_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.alarm_app.R;

public class Good_morning extends Activity {
Button conti;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_good_morning);
		conti=(Button)findViewById(R.id.cont_g);
		conti.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mrng=new Intent(Good_morning.this,Out_bed.class);
				startActivity(mrng);
			}
		});
	}
}
