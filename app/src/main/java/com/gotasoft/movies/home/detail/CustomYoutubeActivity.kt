package com.gotasoft.movies.home.detail

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gotasoft.movies.R
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView

/**
 * Created by dattien on 6/2/18.
 */

class CustomYoutubeActivity : AppCompatActivity() {

    private var videoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_youtube)
        videoId = intent.getStringExtra("EXTRA_VIDEO_ID")
        initYouTubePlayerView()
    }

    private fun initYouTubePlayerView() {
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        youTubePlayerView.playerUIController.showYouTubeButton(false)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.initialize({ youTubePlayer ->
            youTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    loadVideo(youTubePlayer, videoId)
                }
            })
        }, true)

    }

    private fun loadVideo(youTubePlayer: YouTubePlayer, videoId: String) {
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            youTubePlayer.loadVideo(videoId, 0f)
        } else {
            youTubePlayer.cueVideo(videoId, 0f)
        }
    }
}
