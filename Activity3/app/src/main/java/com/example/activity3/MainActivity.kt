package com.example.activity3

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    private var sensorX: EditText? = null;
    private var sensorY: EditText? = null;
    private var sensorZ: EditText? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeVariables()
    }

    private fun initializeVariables() {
        sensorX = findViewById<EditText>(R.id.sensor_x_id)
        sensorY = findViewById<EditText>(R.id.sensor_y_id)
        sensorZ = findViewById<EditText>(R.id.sensor_z_id)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        sensorX?.setText("X: $x")
        sensorY?.setText("Y: $y")
        sensorZ?.setText("Z: $z")
        if(x>11 || y>11 || z>11)
            sendMessage()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun sendMessage() {
        val intent = Intent(this, ShowMessage::class.java)
        startActivity(intent)
    }
}