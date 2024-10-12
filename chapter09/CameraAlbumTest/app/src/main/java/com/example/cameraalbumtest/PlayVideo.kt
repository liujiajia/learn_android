package com.example.cameraalbumtest

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayVideo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_play_video)
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //     insets
        // }

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        val videoView = findViewById<VideoView>(R.id.video_view)
        videoView.setVideoURI(uri)
        findViewById<Button>(R.id.play).setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }
        findViewById<Button>(R.id.pause).setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        findViewById<Button>(R.id.replay).setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            } else {
                videoView.seekTo(0)
                videoView.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<VideoView>(R.id.video_view).suspend()
    }
}