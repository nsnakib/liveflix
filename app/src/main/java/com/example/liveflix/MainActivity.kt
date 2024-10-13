package com.example.liveflix

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)
        player = ExoPlayer.Builder(this).build()

        // Set the PlayerView's player
        playerView.player = player

        // Video URL to be played
        val videoUri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")

        // Create a MediaItem
        val mediaItem = MediaItem.fromUri(videoUri)

        // Set the media item to be played
        player.setMediaItem(mediaItem)

        // Prepare the player
        player.prepare()

        // Start playing the video
        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}
