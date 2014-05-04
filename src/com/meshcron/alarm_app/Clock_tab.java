package com.meshcron.alarm_app;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.alarm_app.R;

public class Clock_tab extends Fragment{
	TextView txtCurrentTime;
	TextView txtCurrentSecond;
	TextView am;
	RelativeLayout clock;
	ToggleButton change;
	Thread myThread;
	Thread myThreadd;
	TextView date;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_clock, container, false);
		clock=(RelativeLayout)rootView.findViewById(R.id.rel_clock);
		clock.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.e("inside","ontouch");
				 Date dt = new Date();
		         int hours = dt.getHours();
		         int minutes = dt.getMinutes();
		         SharedPreferences preferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putInt("hour",hours);
					editor.putInt("minutes",minutes);
					editor.commit();
					Intent clock=new Intent(getActivity(),Sleepy_Value.class);
					startActivity(clock);
				return false;
			}
		});
		 myThread = null;
		 Runnable myRunnableThread = new CountDownRunner();
		    myThread= new Thread(myRunnableThread);
		    myThread.start();
		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/DroidSans.ttf");
	 txtCurrentTime= (TextView)rootView.findViewById(R.id.textView1);
	 txtCurrentSecond= (TextView)rootView.findViewById(R.id.textView2);
	 am=(TextView)rootView.findViewById(R.id.textView3);
	 change=(ToggleButton)rootView.findViewById(R.id.toggle_change);
	change.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked==true){
			 myThreadd = null;
				myThread.interrupt();
				 Runnable myRunnableThreadd = new CountDownRunnerr();
				    myThreadd= new Thread(myRunnableThreadd);
				    myThreadd.start();
			}
			else if(isChecked==false){
				myThreadd.interrupt();
				 Runnable myRunnableThread = new CountDownRunner();
				    myThread= new Thread(myRunnableThread);
				    myThread.start();
			}
		}
	});
	 
	 txtCurrentSecond.setTypeface(tf);
		return rootView;  
	}
	
	public void doWork() {
	    getActivity().runOnUiThread(new Runnable() {
	        public void run() {
	            try{
	                    Date dt = new Date();
	                    int hours = dt.getHours();
	                    int minutes = dt.getMinutes();
	                    int seconds = dt.getSeconds();
	                    if(minutes<10){
	                    	 String curTime = hours + ":" +"0"+ minutes;
	 	                   // String second=String.valueOf(seconds);
	 	                    am.setVisibility(View.INVISIBLE);
	 	                    txtCurrentTime.setText(curTime);
	 	                    //txtCurrentSecond.setText(second);
	                    	}
	                    	else{
	                    		 String curTime = hours + ":" + minutes;
	     	                    //String second=String.valueOf(seconds);
	     	                    am.setVisibility(View.INVISIBLE);
	     	                    txtCurrentTime.setText(curTime);
	     	                   // txtCurrentSecond.setText(second);
	                    	}
	                    	if(seconds<10){
	                    		// String curTime = hours + ":" + minutes;
	     	                    String second=String.valueOf(seconds);
	     	                    am.setVisibility(View.INVISIBLE);
	     	                   // txtCurrentTime.setText(curTime);
	     	                    txtCurrentSecond.setText("0"+second);
	                    	}
	                    	else{
	                    		 //String curTime = hours + ":" + minutes;
	     	                    String second=String.valueOf(seconds);
	     	                    am.setVisibility(View.INVISIBLE);
	     	                   // txtCurrentTime.setText(curTime);
	     	                    txtCurrentSecond.setText(second);
	                    	}
	                   
	            }catch (Exception e) {}
	        }
	    });
	}
	
	
	public void doWorkk() {
	    getActivity().runOnUiThread(new Runnable() {
	        public void run() {
	            try{
	                    Date dt = new Date();
	                    int hours = dt.getHours();
	                    int minutes = dt.getMinutes();
	                    int seconds = dt.getSeconds();
	                    String curTime = hours + ":" + minutes;
	                    String second=String.valueOf(seconds);
	                    
	                    if(hours>12)
	                    {
	                    	if(minutes<10){
	                        txtCurrentTime.setText(String.valueOf(hours-12)+ ":"+"0"+(String.valueOf(minutes)));
	                    	}
	                    	else{
	                    		txtCurrentTime.setText(String.valueOf(hours-12)+ ":"+(String.valueOf(minutes)));
	                    	}
	                    	if(seconds<10){
	                        txtCurrentSecond.setText("0"+second);
	                    	}
	                    	else{
	                    		txtCurrentSecond.setText(second);
	                    	}
	                        am.setVisibility(View.VISIBLE);
	                        am.setText("PM");
	                        
	                    }
	                    if(hours==12)
	                    {
	                    	if(minutes<10){
		                        txtCurrentTime.setText("0"+String.valueOf(hours-12)+ ":"+"0"+(String.valueOf(minutes)));
		                    	}
		                    	else{
		                    		txtCurrentTime.setText("0"+String.valueOf(hours-12)+ ":"+(String.valueOf(minutes)));
		                    	}
		                    	if(seconds<10){
		                        txtCurrentSecond.setText("0"+second);
		                    	}
		                    	else{
		                    		txtCurrentSecond.setText(second);
		                    	}
	                        am.setVisibility(View.VISIBLE);
	                        am.setText("PM");
	                       
	                    }
	                    if(hours<12)
	                    {
	                    	if(minutes<10){
		                        txtCurrentTime.setText(String.valueOf(hours)+ ":"+"0"+(String.valueOf(minutes)));
		                    	}
		                    	else{
		                    		txtCurrentTime.setText(String.valueOf(hours)+ ":"+(String.valueOf(minutes)));
		                    	}
		                    	if(seconds<10){
		                        txtCurrentSecond.setText("0"+second);
		                    	}
		                    	else{
		                    		txtCurrentSecond.setText(second);
		                    	}
	                        am.setVisibility(View.VISIBLE);
	                        am.setText("AM");
	                        
	                    }
	            }catch (Exception e) {}
	        }
	    });
	}

	class CountDownRunner implements Runnable{
	    // @Override
	    public void run() {
	            while(!Thread.currentThread().isInterrupted()){
	                try {
	                doWork();
	                    Thread.sleep(1000); // Pause of 1 Second
	                } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                }catch(Exception e){
	                }
	            }
	    }
	}
	
	class CountDownRunnerr implements Runnable{
	    // @Override
	    public void run() {
	            while(!Thread.currentThread().isInterrupted()){
	                try {
	                doWorkk();
	                    Thread.sleep(1000); // Pause of 1 Second
	                } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                }catch(Exception e){
	                }
	            }
	    }
	}

}