package com.example.golfie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.AsyncTask;

import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import android.hardware.SensorManager;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private socket s;
    private EditText e1;
    private PrintWriter writer;

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered accelerometer listener");

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered accelerometer listener");

    }

    @Override
    protected  void OnSensorChanged(SensorEvent sensorEvent )
    {
        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChangedAccelerometer : X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
            BackgroundTask b1 = new BackgroundTask();
            String msg = "AccelerometerData" + " " + String.valueOf(sensorEvent.values[0]);
            msg = msg + " " + String.valueOf(sensorEvent.values[1]) + " " + String.valueOf(sensorEvent.values[2]);
            b1.execute(msg);
        }

        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG, "onSensorChangedGyroscope : X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
            BackgroundTask b1 = new BackgroundTask();
            String msg = "GyroscopeData" + " " + String.valueOf(sensorEvent.values[0]);
            msg = msg + " " + String.valueOf(sensorEvent.values[1]) + " " + String.valueOf(sensorEvent.values[2]);
            b1.execute(msg);
        }
    }

    static class BackgroundTask extends AsyncTask<String,Void,Void>
        {
            @Override
            protected Void doInBackground(String... voids)
            {
                private ip="";
                private port=;
                try{
                    String msg = voids[0];
                    s = new Socket(ip,port);
                    writer = new PrintWriter(s.getOutputStream());
                    writer.write(msg);
                    writer.flush();
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                return null;
            }
        }

    }
















}
