package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
        if (!extraData.isNullOrEmpty()) {
            // Toast.makeText(this, extraData, Toast.LENGTH_SHORT).show()
            findViewById<TextView>(R.id.text1).text = extraData
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "我是返回的值")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}