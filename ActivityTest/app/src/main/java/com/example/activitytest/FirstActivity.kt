package com.example.activitytest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity", "Task id is $taskId")
        setContentView(R.layout.first_layout)

        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(this, "You clicked Button 1", Toast.LENGTH_SHORT).show()
            // finish()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            // 显式intent
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button3)!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        findViewById<Button>(R.id.button4)!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val data = "我是传递的数据SecondActivity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data", data)
            startActivity(intent)
        }

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.i("getContent","result code is:${it.resultCode}")
            val value = it.data?.getStringExtra("data_return")
            Log.i("getContent","value is $value")
            findViewById<TextView>(R.id.text1).text = value

        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            // startActivityForResult(intent, 1)
            getContent.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}