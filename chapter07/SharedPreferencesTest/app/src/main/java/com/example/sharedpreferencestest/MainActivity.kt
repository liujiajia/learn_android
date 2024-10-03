package com.example.sharedpreferencestest

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "Jimmy")
            editor.putInt("age", 39)
            editor.putBoolean("married", true)
            editor.apply()
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.clear()
            editor.apply()
        }

        findViewById<Button>(R.id.restoreButton).setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)

            Toast.makeText(this,"Name: ${name}, Age: $age, Married: $married", Toast.LENGTH_SHORT).show()
        }
    }
}