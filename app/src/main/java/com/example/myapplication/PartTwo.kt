package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.VideoView

class PartTwo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_two)

        val num = intent.getIntExtra("INTEGER_KEY",0)
        Log.d("The Num, Part Two", num.toString())

        lateinit var videoView: VideoView
        lateinit var mediaPlayer: MediaPlayer
        val mcurrentPosition: Int = 0
        var videoRoute = 1
        when (num){
            1 ->  videoRoute = R.raw.squats
            2 -> videoRoute = R.raw.situps
            3 -> videoRoute = R.raw.arm
            4 -> videoRoute = R.raw.pushup
            5 -> videoRoute = R.raw.legs
        }
        val video = findViewById<VideoView>(R.id.videoView)


        val url = Uri.parse("android.resource://"
                + packageName
                + "/"
                + videoRoute)

        video.setVideoURI(url)
        video.start()
        video.setOnPreparedListener{ mp ->
            mediaPlayer = mp
            mediaPlayer.isLooping = true
            if(mcurrentPosition != 0) {
                mediaPlayer.seekTo(mcurrentPosition)
                mediaPlayer.start()
            }
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.clearCache(true)
        val randomQuery = System.currentTimeMillis().toString()
        try { webView.loadUrl("http://192.168.0.7:5000/$num?random=$randomQuery") }
        catch (e: Exception){
            val myInt = Intent(this@PartTwo,Menu::class.java)
            startActivity(myInt)
        }


        val exitBtn = findViewById<Button>(R.id.leaveBtn)
        exitBtn.setOnClickListener{
            webView.loadUrl("")
            val myInt = Intent(this@PartTwo,Menu::class.java)
            startActivity(myInt)
            finish()
        }
    }
}