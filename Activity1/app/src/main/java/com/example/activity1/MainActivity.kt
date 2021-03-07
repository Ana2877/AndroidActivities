package com.example.activity1
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast;


class MainActivity : AppCompatActivity() {

    private var sum: Int = 0;
    private var inputNumber1: EditText? = null;
    private var inputNumber2: EditText? = null;
    private var outputSum: TextView? = null;
    private var sumButton: Button ? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeVariables();
        sumButtonListener();
        // The activity is create
    }

    private fun initializeVariables() {
        inputNumber1 = findViewById<EditText>(R.id.input_number_1)
        inputNumber2 = findViewById<EditText>(R.id.input_number_2)
        outputSum = findViewById<TextView>(R.id.output_sum_id)
        sumButton = findViewById<Button>(R.id.sum_button_id);
    }

    private fun sumButtonListener(){
        sumButton?.setOnClickListener{
            sum = (inputNumber1?.text.toString().toInt()) + (inputNumber2?.text.toString().toInt())
            outputSum?.text = "Sum Result: $sum";
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show()
        // The activity is about to become visible.
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show()
        //The activity has become visible (now it "resumes").
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show()
        // Focus on another activity (this activity is about to be "stopped").
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show()
        // The activity is no longer visible (now it is "stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show()
        // The activity is about to be destroyed.
    }
}
