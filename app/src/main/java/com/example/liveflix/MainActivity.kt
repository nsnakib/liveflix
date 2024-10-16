package com.example.liveflix



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val videoList = loadVideosFromJson()
        videoAdapter = VideoAdapter(videoList) { video ->
            // Ensure you're passing the URL string instead of the entire Video object
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("videoUrl", video.sources[0]) // Pass the video URL here
            startActivity(intent)
        }
        recyclerView.adapter = videoAdapter
    }

    private fun loadVideosFromJson(): List<Video> {
        val inputStream = assets.open("media.json")
        val reader = InputStreamReader(inputStream)
        val videoResponse = Gson().fromJson(reader, VideoResponse::class.java)
        return videoResponse.categories[0].videos
    }
}
