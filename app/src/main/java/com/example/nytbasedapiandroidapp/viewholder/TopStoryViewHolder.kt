package com.example.nytbasedapiandroidapp.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nytbasedapiandroidapp.databinding.TopStoryItemBinding
import com.example.nytbasedapiandroidapp.topstory.models.Result

class TopStoryViewHolder(
    private val binding: TopStoryItemBinding,
    private val onItemClick: (Result) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
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

