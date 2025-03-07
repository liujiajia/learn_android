package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

            val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }

        findViewById<Button>(R.id.sendNotice).setOnClickListener {
            // val notification = NotificationCompat.Builder(this, "normal")
            //     .setContentTitle("This is content title")
            //     .setContentText("This is content text")
            //     .setSmallIcon(R.drawable.small_icon)
            //     .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            //     .build()
            // manager.notify(1, notification)

            // val intent = Intent(this, NotificationActivity::class.java)
            // val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            // val notification = NotificationCompat.Builder(this, "normal")
            //     .setContentTitle("This is content title")
            //     // .setContentText("Learn how to build notifications, send and sync data,\n" +
            //     //         "                        and use voice actions.Get the official Android IDE and developer tools to\n" +
            //     //         "                        build apps for Android.")
            //     // .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build\n" +
            //     //         "notifications, send and sync data, and use voice actions. Get the official\n" +
            //     //         "Android IDE and developer tools to build apps for Android."))
            //     .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
            //     .setSmallIcon(R.drawable.small_icon)
            //     .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            //     .setContentIntent(pi)
            //     // .setAutoCancel(true)
            //     .build()
            // manager.notify(1, notification)


            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setContentText("Learn how to build notifications, send and sync data,\n" +
                        "                        and use voice actions.Get the official Android IDE and developer tools to\n" +
                        "                        build apps for Android.")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                // .setAutoCancel(true)
                .build()
            manager.notify(2, notification)
        }

    }
}