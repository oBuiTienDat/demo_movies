package com.gotasoft.movies.home.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.gotasoft.movies.R
import com.gotasoft.movies.databinding.ActivityYoutubeBinding
import com.thefinestartist.ytpa.enums.Orientation
import com.thefinestartist.ytpa.utils.YouTubeApp

/**
 * Created by dattien on 10/8/17.
 */

class YoutubeActivity : YouTubeBaseActivity(),
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener,
        YouTubePlayer.PlayerStateChangeListener,
        SeekBar.OnSeekBarChangeListener,
        YouTubePlayer.PlaybackEventListener {

    private lateinit var mActivityDetailBinding: ActivityYoutubeBinding
    private val RECOVERY_DIALOG_REQUEST = 1
    private val META_DATA_NAME = "com.thefinestartist.ytpa.YouTubePlayerActivity.ApiKey"
    private var playerStyle: YouTubePlayer.PlayerStyle? = null
    private var orientation: Orientation? = null
    private var showAudioUi: Boolean = false
    private var handleError: Boolean = false
    private var animEnter: Int = 0
    private var animExit: Int = 0
    private var googleApiKey: String? = null

    private var player: YouTubePlayer? = null
    private var videoId: String? = ""
    private var isPlay: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)
        mActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_youtube)
        initView()
    }

    private fun initView() {
        videoId = intent?.getStringExtra("EXTRA_VIDEO_ID")
        mActivityDetailBinding.btnFullscreen.setOnClickListener({ player?.setFullscreen(true) })
        init()
    }

    private fun init() {
        try {
            val ai = packageManager.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA)
            val bundle = ai.metaData
            googleApiKey = bundle.getString(META_DATA_NAME)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        mActivityDetailBinding.youtube.initialize(googleApiKey, this)
        playerStyle = YouTubePlayer.PlayerStyle.MINIMAL
        orientation = Orientation.AUTO;
        showAudioUi = true
        handleError = true
        animEnter = 0
        animExit = 0
    }


    override fun onAdStarted() {
    }

    override fun onLoading() {
    }

    override fun onVideoStarted() {
    }

    override fun onLoaded(p0: String?) {
    }

    override fun onVideoEnded() {
    }

    override fun onError(reason: YouTubePlayer.ErrorReason?) {
        if (handleError && YouTubePlayer.ErrorReason.NOT_PLAYABLE == reason)
            videoId?.let { YouTubeApp.startVideo(this, it) }
    }

    override fun onFullscreen(fullScreen: Boolean) {
        when (orientation) {
            Orientation.AUTO, Orientation.AUTO_START_WITH_LANDSCAPE -> if (fullScreen)
                requestedOrientation = LANDSCAPE_ORIENTATION
            else
                requestedOrientation = PORTRAIT_ORIENTATION
            Orientation.ONLY_LANDSCAPE, Orientation.ONLY_PORTRAIT -> {
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (orientation) {
            Orientation.AUTO, Orientation.AUTO_START_WITH_LANDSCAPE ->
                if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    player?.setFullscreen(true)
                } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    player?.setFullscreen(false)
                }
        }
    }

    @SuppressLint("InlinedApi")
    private val PORTRAIT_ORIENTATION = if (Build.VERSION.SDK_INT < 9)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    else
        ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

    @SuppressLint("InlinedApi")
    private val LANDSCAPE_ORIENTATION = if (Build.VERSION.SDK_INT < 9)
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    else
        ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        this.player = player
        player?.setOnFullscreenListener(this)
        player?.setPlayerStateChangeListener(this)
        player?.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                or YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                or YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                or YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT)
        player?.setPlayerStyle(playerStyle)
        player?.setPlaybackEventListener(this)
        player?.setShowFullscreenButton(true)
        isPlay = wasRestored
        if (!wasRestored) {
            player?.loadVideo(videoId)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason?.isUserRecoverableError() ?: false) {
            errorReason?.getErrorDialog(this, RECOVERY_DIALOG_REQUEST)?.show()
        } else {
            val errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1\$s)",
                    errorReason.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            mActivityDetailBinding.youtube.initialize(googleApiKey, this)
        }
    }

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onSeekTo(p0: Int) {
    }

    override fun onBuffering(p0: Boolean) {
    }

    override fun onPlaying() {
    }

    override fun onStopped() {
    }

    override fun onPaused() {
    }
}

