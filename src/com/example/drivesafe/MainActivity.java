package com.example.drivesafe;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;


public class MainActivity extends Activity implements SensorEventListener{
private float aMagnitude=0;
private SensorManager mSensorManager; private Sensor mAccelerometer;
private final float ALIMIT=7;
private final float BLIMIT=16;

private float moveScore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		aMagnitude=0;
		moveScore=0;
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		}
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
		}
	@Override
	public void onSensorChanged(SensorEvent event) {
		TextView color=(TextView)findViewById(R.id.bg);
		TextView mag=(TextView)findViewById(R.id.magnitude);
		TextView score=(TextView)findViewById(R.id.move_score);
		aMagnitude=(float)(Math.pow((Math.pow(event.values[0],2)+Math.pow(event.values[1],2)+Math.pow(event.values[2],2)),.5))-9.8f;
		mag.setText(Float.toString(aMagnitude));
		
		if (aMagnitude>ALIMIT)
		{
			moveScore+=Math.pow(1.05,aMagnitude);
		}
		if(aMagnitude>BLIMIT)
		{
			color.setText("#ff0000");
		}
		else
		{
			color.setText("#90f030");
		}
		score.setText(Float.toString(moveScore));
	}

}
