package com.example.activity3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowMessage : AppCompatActivity() {

    private var outputMessage: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_message)
        outputMessage = findViewById<TextView>(R.id.output_message_id)
        outputMessage?.text = "There was a significant acceleration!"
    }
}