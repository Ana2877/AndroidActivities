package com.example.activity2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private var outputMessage: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        outputMessage = findViewById<TextView>(R.id.output_message_id)
        val inputMessage = intent.getStringExtra("inputMessage")
        outputMessage?.text = inputMessage
    }
}