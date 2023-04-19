package com.example.nytbasedapiandroidapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytbasedapiandroidapp.activity.LoginActivity
import com.example.nytbasedapiandroidapp.adapter.TopStoriesAdapter
import com.example.nytbasedapiandroidapp.databinding.FragmentMainBinding
import com.example.nytbasedapiandroidapp.firebase.FirebaseAuthHelper
import com.example.nytbasedapiandroidapp.topstory.data.NYTApiService
import com.example.nytbasedapiandroidapp.topstory.database.TopStoriesDatabase
import com.example.nytbasedapiandroidapp.topstory.repository.TopStoriesRepository
import com.example.nytbasedapiandroidapp.topstory.viewmodel.MainViewModel
import com.example.nytbasedapiandroidapp.topstory.viewmodel.MainViewModelFactory

// MainFragment.kt
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val authHelper by lazy { FirebaseAuthHelper(requireContext()) }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TopStoriesRepository(NYTApiService.create(), TopStoriesDatabase.getDatabase(requireContext()).topStoriesDao()))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TopStoriesAdapter(listOf())
        binding.topStoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.topStoriesRecyclerView.adapter = adapter

        viewModel.topStories.observe(viewLifecycleOwner) { topStories ->
            if (topStories != null) {
                adapter.updateTopStories(topStories.results)
            }
        }

        binding.logoutButton.setOnClickListener {
            authHelper.logout()
            redirectToLoginActivity()
        }
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


