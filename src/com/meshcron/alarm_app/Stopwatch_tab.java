package com.meshcron.alarm_app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.alarm_app.R;

public class Stopwatch_tab extends Fragment{
	 float x1,x2;
     float y1, y2;
     ViewSwitcher switcher;
     Button start;
     Button timer_start;
     Button pause;
     Button strt;
     Button reset;
     Button stop;
     TextView stopwatch;
     TextView timer;
     EditText minutes;
     private CountDownTimer countDownTimer; // built in android class
		// CountDownTimer
private long totalTimeCountInMilliseconds; // total count down time in
			// milliseconds
private long timeBlinkInMilliseconds; // start time of start blinking
private boolean blink; // controls the blinking .. on and off

     private GestureDetector gestureScanner;
     private long startTime = 0L; private Handler myHandler = new Handler(); long timeInMillies = 0L; long timeSwap = 0L; long finalTime = 0L;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
		//gestureScanner = new GestureDetector(getActivity(),simpleOnGestureListener);
		switcher=(ViewSwitcher)rootView.findViewById(R.id.ViewSwitcher);
		start=(Button)rootView.findViewById(R.id.btn_start);
		pause=(Button)rootView.findViewById(R.id.btn_stop);
		strt=(Button)rootView.findViewById(R.id.btn_strt);
		reset=(Button)rootView.findViewById(R.id.btn_reset);
		stopwatch=(TextView)rootView.findViewById(R.id.txt_stop);
		stop=(Button)rootView.findViewById(R.id.btn_stopp);
		timer=(TextView)rootView.findViewById(R.id.txt_timer);
		minutes=(EditText)rootView.findViewById(R.id.edt_minutes);
		reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopwatch.setText("00:00");
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				countDownTimer.cancel();
				strt.setVisibility(View.VISIBLE);
				stop.setVisibility(View.GONE);
				minutes.setVisibility(View.VISIBLE);
			}
		});
		 final GestureDetector gesture = new GestureDetector(getActivity(),
		            new GestureDetector.SimpleOnGestureListener() {

		                @Override
		                public boolean onDown(MotionEvent e) {
		                    return true;
		                }

		                @Override
		                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
		                    float velocityY) {
		                    Log.i("inside", "onFling has been called!");
		                    final int SWIPE_MIN_DISTANCE = 120;
		                    final int SWIPE_MAX_OFF_PATH = 250;
		                    final int SWIPE_THRESHOLD_VELOCITY = 200;
		                    try {
		                        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
		                            return false;
		                        if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
		                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                            Log.i("", "Down to Up");
		                            switcher.showNext();
		                        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
		                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
		                            Log.i("", "Up to Down");
		                            switcher.showPrevious();
		                        }
		                    } catch (Exception e) {
		                        // nothing
		                    }
		                    return super.onFling(e1, e2, velocityX, velocityY);
		                }
		            });

		        rootView.setOnTouchListener(new View.OnTouchListener() {
		            @Override
		            public boolean onTouch(View v, MotionEvent event) {
		                return gesture.onTouchEvent(event);
		            }
		        });
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//switcher.showNext();
				startTime = SystemClock.uptimeMillis(); myHandler.postDelayed(updateTimerMethod, 0);
			}
		});
		pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method s
					timeSwap += timeInMillies; 
					myHandler.removeCallbacks(updateTimerMethod);
				
			}
		});
		strt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setTimer();
				stop.setVisibility(View.VISIBLE);
				strt.setVisibility(View.GONE);
			minutes.setVisibility(View.GONE);
				minutes.setText("");
				startTimer();
			}
		});
		
		return rootView;
	}
      

	private void setTimer() {
		int time = 0;
		if (!minutes.getText().toString().equals("")) {
			time = Integer.parseInt(minutes.getText().toString());
		} else
			Toast.makeText(getActivity(), "Please Enter Minutes...",
					Toast.LENGTH_LONG).show();

		totalTimeCountInMilliseconds = 60 * time * 1000;

		timeBlinkInMilliseconds = 30 * 1000;
	}

	private void startTimer() {
		countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
			// 500 means, onTick function will be called at every 500
			// milliseconds

			@Override
			public void onTick(long leftTimeInMilliseconds) {
				long seconds = leftTimeInMilliseconds / 1000;

				if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
					// change the style of the textview .. giving a red
					// alert style

					if (blink) {
						timer.setVisibility(View.VISIBLE);
						// if blink is true, textview will be visible
					} else {
						timer.setVisibility(View.INVISIBLE);
					}

					blink = !blink; // toggle the value of blink
				}

				timer.setText(String.format("%02d", seconds / 60)
						+ ":" + String.format("%02d", seconds % 60));
				// format the textview to show the easily readable format

			}

			@Override
			public void onFinish() {
				// this function will be called when the timecount is finished
				timer.setText("Time up!");
				timer.setVisibility(View.VISIBLE);
				start.setVisibility(View.VISIBLE);
				stop.setVisibility(View.GONE);
				minutes.setVisibility(View.VISIBLE);
			}

		}.start();

	}
	
	private Runnable updateTimerMethod = new Runnable() {

		public void run() {
		timeInMillies = SystemClock.uptimeMillis()-startTime;
		finalTime = timeSwap + timeInMillies;

		int seconds = (int) (finalTime / 1000);
		int minutes = seconds / 60;
		seconds = seconds % 60;
		int milliseconds = (int) (finalTime % 1000);
		stopwatch.setText("" + minutes + ":"
		+ String.format("%02d", seconds) + ":"
		+ String.format("%03d", milliseconds));
		myHandler.postDelayed(this, 0);
		}

		};
		

	}