package com.example.activity2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var inputMessage: EditText? = null;
    private var sendButton: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeVariables()
    }

    private fun initializeVariables() {
        inputMessage = findViewById<EditText>(R.id.input_message_id)
        sendButton = findViewById<Button>(R.id.send_button_id);
    }

    fun send(view: View?) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("inputMessage", inputMessage?.text.toString())
        startActivity(intent)
    }
}