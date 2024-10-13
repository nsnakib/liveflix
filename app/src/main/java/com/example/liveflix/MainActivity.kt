package com.example.liveflix

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var btnVideoStream: Button
    private lateinit var btnLiveStream: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)
        btnVideoStream = findViewById(R.id.btn_video_stream)
        btnLiveStream = findViewById(R.id.btn_live_stream)

        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        // Set up click listener for Video Stream
        btnVideoStream.setOnClickListener {
            playVideoStream()
        }

        // Set up click listener for Live Stream
        btnLiveStream.setOnClickListener {
            playLiveStream()
        }
    }

    private fun playVideoStream() {
        val videoUri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
        val mediaItem = MediaItem.fromUri(videoUri)

        // Set the media item for the player
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }

    private fun playLiveStream() {
        val liveStreamUri = Uri.parse("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8")
        val mediaItem = MediaItem.Builder()
            .setUri(liveStreamUri)
            .setLiveConfiguration(MediaItem.LiveConfiguration.Builder().build())
            .build()

        // Set the live stream media item
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}
