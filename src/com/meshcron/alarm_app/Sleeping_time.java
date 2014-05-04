package com.meshcron.alarm_app;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.FloatMath;
import android.util.Log;
import android.view.Window;

import com.example.alarm_app.R;

public class Sleeping_time extends Activity implements SensorEventListener{
	MediaRecorder mrec;
	File audiofile = null;
	private static final String TAG="SoundRecordingDemo";
	private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor lightSensor;
    private Handler mHandler;
    Runnable run;
    float x;
    float mAccelCurrent;
    private float mLastX, mLastY, mLastZ;
    private final float NOISE = (float) 3.0;

	protected void startRecording() throws IOException 
	{
	   mrec.setAudioSource(MediaRecorder.AudioSource.MIC);
	   mrec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	   mrec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	   if (audiofile == null) 
	   {
	       File sampleDir = Environment.getExternalStorageDirectory();
	       try
	       { 
	          audiofile = File.createTempFile("ibm", ".3gp", sampleDir);
	       }
	       catch (IOException e) 
	       {
	           Log.e(TAG,"sdcard access error");
	           return;
	       }
	   }
	   mrec.setOutputFile(audiofile.getAbsolutePath());
	   mrec.prepare();
	   mrec.start();
	}
	protected void stopRecording() 
	{
	   mrec.stop();
	   mrec.release();
	   processaudiofile();
	}
	protected void processaudiofile() 
	{
	   ContentValues values = new ContentValues(3);
	   long current = System.currentTimeMillis();
	   values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
	   values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
	   values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
	   values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
	   ContentResolver contentResolver = getContentResolver();
	    
	   Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	   Uri newUri = contentResolver.insert(base, values);
	    
	   sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sleeping_time);
		sensorManager = (SensorManager)Sleeping_time.this.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
		final Handler handler = new Handler();
		mrec=new MediaRecorder();
		try {
			startRecording();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
		    // Launch new Intent here
			  stopRecording();
			  Float accel=mAccelCurrent;
			  Float light=x;
			  Intent sleep=new Intent(Sleeping_time.this,Awake.class);
			  sleep.putExtra("accel",accel);
			  sleep.putExtra("light",light);
			  startActivity(sleep);
		  }
		}, ((1000 * 60) * 10));
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		 x = event.values[0];
	    float y = event.values[1];
	    float z = event.values[2];
	     mAccelCurrent = FloatMath.sqrt(x*x + y*y + z*z);
	}
}