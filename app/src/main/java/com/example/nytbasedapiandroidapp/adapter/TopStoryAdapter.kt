package com.example.nytbasedapiandroidapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytbasedapiandroidapp.databinding.TopStoryItemBinding
import com.example.nytbasedapiandroidapp.topstory.models.Result

class TopStoriesAdapter(
    private var topStories: List<Result>,
    private val onItemClick: (Result) -> Unit
) : RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder>() {

    inner class TopStoryViewHolder(private val binding: TopStoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Result) {
            binding.title.text = story.title
            binding.byline.text = story.byline
            binding.publishedDate.text = story.published_date
            // You can bind other fields as needed

            itemView.setOnClickListener {
                onItemClick(story)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoryViewHolder {
        val binding = TopStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopStoryViewHolder, position: Int) {
        holder.bind(topStories[position])
    }

    override fun getItemCount(): Int {
        return topStories.size
    }

    fun updateTopStories(newTopStories: List<Result>) {
        topStories = newTopStories
        notifyDataSetChanged()
    }
}
