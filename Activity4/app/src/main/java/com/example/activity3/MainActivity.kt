package com.example.activity3

import GPSTracker
import android.Manifest
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var mLightSensor: Sensor? = null
    private var mGyroscopeSensor: Sensor? = null
    private var lightValue: TextView? = null
    private var gyroscopeX: TextView? = null
    private var gyroscopeY: TextView? = null
    private var gyroscopeZ: TextView? = null
    private var latitude: TextView? = null
    private var longitude: TextView? = null
    private var gps: GPSTracker? = null
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeLightSensor()
        initializeGyroscopeSensor()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            123
        );
        initializeGPSInfo()
        getGPSInfo()
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, mGyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    private fun initializeLightSensor() {
        lightValue = findViewById<TextView>(R.id.light_id)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLightSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (mLightSensor != null) {
            sensorManager!!.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            lightValue?.text = "Light sensor not supported"
        }
    }

    private fun initializeGyroscopeSensor() {
        gyroscopeX = findViewById<TextView>(R.id.gyroscope_x_id)
        gyroscopeY = findViewById<TextView>(R.id.gyroscope_y_id)
        gyroscopeZ = findViewById<TextView>(R.id.gyroscope_z_id)
        mGyroscopeSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        val sensor: Sensor = event.sensor
        if (sensor.type === Sensor.TYPE_LIGHT) {
            lightValue?.text = "Light Intensity: " + event.values[0]
        }
        if (sensor.type === Sensor.TYPE_GYROSCOPE) {
            gyroscopeX?.text = "Gyroscope X: " + event.values[0]
            gyroscopeY?.text = "Gyroscope Y: " + event.values[1]
            gyroscopeZ?.text = "Gyroscope Z: " + event.values[2]
        }
    }

    private fun initializeGPSInfo() {
        latitude = findViewById<TextView>(R.id.latitude_id)
        longitude = findViewById<TextView>(R.id.longitude_id)
    }

    private fun getGPSInfo() {
        gps = GPSTracker(applicationContext)
        location = gps!!.getLocation()
        if (location != null) {
            latitude?.text = "Latitude: " + location!!.latitude
            longitude?.text = "Longitude: " + location!!.longitude
        }
    }

}