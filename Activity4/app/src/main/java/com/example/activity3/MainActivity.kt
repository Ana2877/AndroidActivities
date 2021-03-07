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
    private var mLightSensor: Sensor? = null
    private var mGyroscopeSensor: Sensor? = null
    var lightValue: TextView? = null
    var gyroscopeX: TextView? = null
    var gyroscopeY: TextView? = null
    var gyroscopeZ: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeLightSensor()
        initializeGyroscopeSensor()
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
        if(mLightSensor != null)
        {
            sensorManager!!.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }else
        {
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
}