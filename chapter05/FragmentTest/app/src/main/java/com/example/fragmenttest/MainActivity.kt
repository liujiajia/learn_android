package com.example.fragmenttest

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //     insets
        // }

        val x = supportFragmentManager.findFragmentById(R.id.rightFrag)
        if (x != null) {
            findViewById<Button>(R.id.button)?.setOnClickListener {
                if (x is RightFragment) {
                    replaceFragment(AnotherRightFragment())
                } else {
                    replaceFragment(RightFragment())
                }
            }
            replaceFragment(RightFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightFrag, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}