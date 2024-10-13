package com.example.liveflix





import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.InputStreamReader

class VideoListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val videoList = loadVideosFromJson() // This should return List<Video>
        videoAdapter = VideoAdapter(videoList) { video ->
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("videoUrl", video.sources[0]) // Make sure this is a String
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

