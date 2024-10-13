package com.example.liveflix



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class VideoAdapter(
    private val videos: List<Video>,
    private val onClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.video_title)
        val description: TextView = itemView.findViewById(R.id.video_description)
        val thumbnail: ImageView = itemView.findViewById(R.id.video_thumbnail)

        init {
            itemView.setOnClickListener {
                onClick(videos[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.title.text = video.title
        holder.description.text = video.description

        // Load thumbnail using Glide
        Glide.with(holder.thumbnail.context)
            .load(video.thumb)
            .error(R.drawable.ic_launcher_foreground) // Placeholder in case of error
            .into(holder.thumbnail)
    }

    override fun getItemCount() = videos.size
}
