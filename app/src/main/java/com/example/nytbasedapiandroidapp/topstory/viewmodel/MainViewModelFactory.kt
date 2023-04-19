package com.example.nytbasedapiandroidapp.topstory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytbasedapiandroidapp.topstory.repository.TopStoriesRepository

class MainViewModelFactory(private val topStoriesRepository: TopStoriesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(topStoriesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}