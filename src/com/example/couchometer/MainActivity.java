package com.example.couchometer;

import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object
	TextView tTime;
	TextView editText1;
	TextView seekbarprint;
	SeekBar seekBar1;
	
	//initialize sum of squares as zero and counter as 0
	float gtm = 0;
	float gt = 0;
	int counter = 0;
	float total = 0;
	float avg = 0;
	int timesat = 0;
	float size = 2;
	boolean isTooLong = false;
	int currentTime = 0;
	
	long endtime = System.currentTimeMillis();

	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
		editText1 = (TextView)findViewById(R.id.editText1);
		seekbarprint = (TextView)findViewById(R.id.seekbarprint);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				size = arg1;
				seekbarprint.setText("Break every " + (int)size + " minute(s).");
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		//xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		//yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		//zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		//tTime=(TextView)findViewById(R.id.ttime);
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

		
		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress, 
			boolean fromUser) { 
    // TODO Auto-generated method stub 
	}
	
	@SuppressLint("NewApi")
	public void tooLong() {
		Intent intent = new Intent();
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		Notification noti = new Notification.Builder(this)
		.setTicker("Couchometer: ")
		.setContentTitle("You've been sitting for " + (int)size + " minutes.")
		.setContentText("Time to take a break!!!")
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentIntent(pIntent).getNotification();
		noti.flags=Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(0, noti); 
	}
	
	public void onSensorChanged(SensorEvent event){
	
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			long t = System.currentTimeMillis()-endtime;
			gtm = gt;
			gt = x * x + y * y + z * z;
			counter++;
			if(gt != 0 && gtm != 0)
				total = total + Math.abs(gt-gtm);
			if(t > 10000)
			{
				avg = total/counter;
				if(avg < 15)
				{
					timesat += 10;
					currentTime += 10;
					
				}
				total = 0; counter = 0; gt= 0; gtm =0;
				endtime = System.currentTimeMillis();
			}
			
			if(currentTime / 60 == size)
			{
				tooLong();
				currentTime = 0;
			}
			
			//xCoor.setText("X: "+x);
			//yCoor.setText("Y: "+y);
			//zCoor.setText("Z: "+z);
			//tTime.setText("T: "+timesat);
			if(timesat < 3600)
				editText1.setText(timesat / 60 + " " +
						"minutes");
			else
				editText1.setText(timesat / 3600 + " hours and " + timesat / 60 + " minutes");
			
		}
	}
}
	