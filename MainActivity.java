package com.example.rndgenerate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
//---------------------------------------------------------
//
// The random number generator of the Android OS is corrected by some digits from the readings of the built-in gyroscope.
// In this application version, only the signal from channel #0 is used.
// This is done only for a simple analysis of the work of the Java code.
// It is easy to see how the performance of such a generator can be improved.
// For example, use data from channels #1 and #2 of the gyroscope for separate function ThreadLocalRandom call cycles
// You can add some values from the data bits from the magnetometer and accelerometer.
// This Java code is convenient in that the random variable will always contain a new random number from the given range. Just read it and use it.
//
// Valery Shmelev (Oflameron)
//
//---------------------------------------------------------

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    public static float gravity0;
    public static float gravity1;
    public static float gravity2;
    public int random = 0; // Random value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //-------------------------------------------------
        // List of Android sensors
        //-------------------------------------------------
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> listSensorType = new ArrayList<>();
        for (int i = 0; i < deviceSensors.size(); i++) {
            listSensorType.add(deviceSensors.get(i).getName());
            Log.i("ALL Sensores", " == == Sensor List == == " + deviceSensors.get(i).getName().toString());

        }
        //-------------------------------------------------
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), // To determine the relative orientation of the device in space m/s 2
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("Sensor", " == == Registered == == ");

    } // OnCreate



    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            // if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gravity0 = event.values[0];
            gravity1 = event.values[1];
            gravity2 = event.values[2];
            // Log.i("Gyro", " == == Gyroscope 0 == == " + gravity0*100000);
            // Log.i("Gyro", " == == Gyroscope 1 == == " + gravity1*100000);
            // Log.i("Gyro", " == == Gyroscope 2 == == " + gravity2*100000);

            int e = (int) Math.rint(gravity0*100000);
            int g = Math.abs(e);
            // Log.i("Math", " == == Math.abs(e) Math.rint(gravity0) == == " + g);
            String Digit = String.valueOf(g); // Convert to string
            int l = Digit.length(); // Lenght of string Digit

            String asubstring = Digit.substring(1, 2);
            // Log.i("Math 2", " == == Math.abs(e) Math.rint(gravity0) == == " + asubstring); // One digit as string
            int in = Integer.valueOf(asubstring); // Convert string to int
            // Log.i("Math 3", " == == Math.abs(e) Math.rint(gravity0) == == " + in);

            // This Java code is convenient in that the random variable will always contain a new random number from the given range. Just read it and use it.
            // The number of cycles of calls to the random number generator is also random.
            for(int i = 0; i < in+3; i++) { // The number of repetitions depends on the operation of the gyroscope (one of the channels)
                random = ThreadLocalRandom.current().nextInt(1, 90);
            }
            Log.i("GENERATED", " == == http://oflameron.com Your Rundom NUMBER == == " + random);
            

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}