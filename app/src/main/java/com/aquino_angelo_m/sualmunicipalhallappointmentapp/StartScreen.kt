package com.aquino_angelo_m.sualmunicipalhallappointmentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.app.ActivityOptionsCompat

class StartScreen : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.start_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        videoView = findViewById(R.id.videobackground)
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.bg_video}")
        videoView.setVideoURI(uri)
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.start()

        val startBtn = findViewById<Button>(R.id.button)
        val logo = findViewById<ImageView>(R.id.logo)
        val startText = findViewById<ImageView>(R.id.text)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        startBtn.startAnimation(fadeInAnimation)
        startText.startAnimation(fadeInAnimation)
        logo.startAnimation(fadeInAnimation)

        startBtn.setOnClickListener {
            val intent = Intent(this, PickingPage::class.java)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                logo, "transition_name"
            )

            startActivity(intent, options.toBundle())
        }
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView.start()
    }
}
