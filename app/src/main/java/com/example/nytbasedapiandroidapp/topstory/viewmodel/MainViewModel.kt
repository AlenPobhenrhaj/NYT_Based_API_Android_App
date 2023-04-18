package com.example.nytbasedapiandroidapp.topstory.viewmodel

// viewmodel/MainViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytbasedapiandroidapp.topstory.models.TopStories
import com.example.nytbasedapiandroidapp.topstory.repository.TopStoriesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TopStoriesRepository) : ViewModel() {
    private var _topStories: MutableLiveData<TopStories?> = MutableLiveData()
    val topStories: LiveData<TopStories?> = _topStories

    init {
        fetchTopStories()
    }

    private fun fetchTopStories() {
        viewModelScope.launch {
            _topStories.value = repository.getTopStories()
        }
    }
}
