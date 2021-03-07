package com.example.activity3

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var light: Sensor? = null
    var lightValue: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lightValue = findViewById<TextView>(R.id.light_id);
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        light = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null)
        {
            sensorManager!!.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightValue?.text = "Light sensor not supported";
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val sensor: Sensor = event.sensor
        if (sensor.type === Sensor.TYPE_LIGHT) {
            lightValue?.text = "Light Intensity: " + event.values[0]
        }
    }
}