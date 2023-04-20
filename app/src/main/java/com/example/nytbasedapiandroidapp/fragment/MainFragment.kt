package com.example.nytbasedapiandroidapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
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
import com.squareup.picasso.Picasso

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

        val adapter = TopStoriesAdapter(listOf()) { result ->
            binding.articleTitle.text = result.title
            binding.articleByline.text = result.byline
            binding.articleAbstract.text = result.abstract
            binding.articlePublishedDate.text = result.published_date

            val multimedia = result.multimedia?.firstOrNull()
            val imageUrl = multimedia?.url
            imageUrl?.let { imageUrl ->
                Picasso.get().load(imageUrl).into(binding.articleImage)
            }

            if (binding.articleDetailsContainer.visibility == View.GONE) {
                binding.articleDetailsContainer.visibility = View.VISIBLE
            } else {
                binding.articleDetailsContainer.visibility = View.GONE
            }
        }


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

    private fun animateRecyclerView(shouldMoveToLeft: Boolean) {
        val animationDuration = 300L // You can adjust the duration as needed
        val fromX = if (shouldMoveToLeft) 0f else -binding.topStoriesRecyclerView.width.toFloat()
        val toX = if (shouldMoveToLeft) -binding.topStoriesRecyclerView.width.toFloat() else 0f
        val translateAnimation = TranslateAnimation(fromX, toX, 0f, 0f).apply {
            duration = animationDuration
            fillAfter = true
        }

        binding.topStoriesRecyclerView.startAnimation(translateAnimation)
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}