package com.example.motivateme

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Declare our reference to views before layout inflation
    private lateinit var name: EditText
    private lateinit var message: TextView
    //Override the lifecycle onCreate method.  This should initialize all important items for the code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate the given layout xml views into kotlin objects
        setContentView(R.layout.activity_main)

        //Get a reference to the button view from our layout and set a clickListener
        val updateButton: Button = findViewById(R.id.activity_main_bt_update)
        updateButton.setOnClickListener {
            updateMessage()
        }

        //Set the value to our declared views
        name = findViewById(R.id.acitivity_main_et_name)
        message = findViewById(R.id.main_activity_tv_message)

        //Check if a bundle is present, if it is not savedInstanceState will be null
        if (savedInstanceState != null){
            //Get the message from the bundle and set the text
            val savedMessage = savedInstanceState.getString("myMessage")
            message.text = savedMessage
        }
    }
    //Override the lifecycle method onSaveInstanceState to save important data
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Save the current message
        outState.putString("myMessage", message.text.toString())
        Log.i("TEST", "onSaveInstanceState() called.")
    }

    //Get a name from an editText and display a personalized message
    private fun updateMessage(){

        //Get the username from the editText
        val username = name.text

        //Pick a motivational message
        val motivationalMessage = listOf("Keep Working Hard!", "Never Give Up!", "What doesn't kill you...", "Every day is better than the next.", "Do or do not, there is no try")
        val index = (0..4).random()
        val currentMessage = motivationalMessage[index]

        //Update the textView to display our message
        if (username.toString() == ""){
            message.text = "Please enter a name!"
            return
        } else {
            message.text = "Hello $username! $currentMessage"
        }

        //Clear the editText and use setText because editText is an editable view
        name.setText("")

        hideKeyboard()

    }

    //Hide the keyboard using an InputMethodManager
    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(name.windowToken,0)
    }
}