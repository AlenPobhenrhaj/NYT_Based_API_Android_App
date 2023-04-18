package com.example.nytbasedapiandroidapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.nytbasedapiandroidapp.activity.LoginActivity
import com.example.nytbasedapiandroidapp.databinding.FragmentMainBinding
import com.example.nytbasedapiandroidapp.firebase.FirebaseAuthHelper
import com.example.nytbasedapiandroidapp.topstory.data.NYTApiService
import com.example.nytbasedapiandroidapp.topstory.database.AppDatabase
import com.example.nytbasedapiandroidapp.topstory.repository.TopStoriesRepository
import com.example.nytbasedapiandroidapp.topstory.viewmodel.MainViewModel

// MainFragment.kt


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val authHelper by lazy { FirebaseAuthHelper(requireContext()) }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TopStoriesRepository(NYTApiService.create(), AppDatabase.getInstance(requireContext()).topStoriesDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel.topStories.observe(viewLifecycleOwner, Observer { topStories ->
            // Update your UI with the fetched data
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

