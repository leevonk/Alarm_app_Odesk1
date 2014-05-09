package com.meshcron.alarm_app;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.alarm_app.R;
import com.meshcron.adapter.TabsPagerAdapter;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ImageButton alarm;
	private ImageButton clock;
	private ImageButton stopwatch;
	private RelativeLayout rel;
	// Tab icons

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//Putting one debug button to test quickly
		Button bt = (Button)findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(
						MainActivity.this);
	            builderSingle.setIcon(R.drawable.ic_launcher);
	            builderSingle.setTitle("Select Screen");
	            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	            		MainActivity.this,
	                    android.R.layout.select_dialog_item);
	            arrayAdapter.add("S7-Good Morning Screen");
	            arrayAdapter.add("S12-Set InBed Time Screen");
	            builderSingle.setNegativeButton("cancel",
	                    new DialogInterface.OnClickListener() {

	                        @Override
	                        public void onClick(DialogInterface dialog, int which) {
	                            dialog.dismiss();
	                        }
	                    });

	            builderSingle.setAdapter(arrayAdapter,
	            		new DialogInterface.OnClickListener() {

	            	@Override
	            	public void onClick(DialogInterface dialog, int which) {
	            		String strName = arrayAdapter.getItem(which);
	            		if(strName.equals("S7-Good Morning Screen")){
	            			Intent less=new Intent(MainActivity.this,Good_morning.class);
	            			startActivity(less);
	            		}else if(strName.equals("S12-Set InBed Time Screen")){
	            			Intent i = new Intent(MainActivity.this,SetInBedTime.class);
	            			startActivity(i);
	            		}
	            	}
	            });
	            builderSingle.show();
			}
		});
		// Initilization
		viewPager = (ViewPager)findViewById(R.id.pager);
		alarm=(ImageButton)findViewById(R.id.img_alarm);
	    clock=(ImageButton)findViewById(R.id.img_clock);
		stopwatch=(ImageButton)findViewById(R.id.img_stopwatch);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		alarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
			}
		});
		clock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
			}
		});
		// Adding Tabs
		stopwatch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(2);
			}
		});

		/**
		 * on swiping the viewpager make respective tab selected
		 **/
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				
				viewPager.setCurrentItem(position);
				}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageScrollStateChanged(int position) {
			}
		});
	}
}