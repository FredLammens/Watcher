package com.example.watcher.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.watcher.data.MoviesRepository
import com.example.watcher.data.local.MoviesDatabase
import com.example.watcher.databinding.FragmentMoviesOverviewBinding
import com.example.watcher.models.movies.Result
import com.example.watcher.ui.adapters.MovieAdapter
import com.example.watcher.ui.adapters.MovieClickListener
import com.example.watcher.ui.viewmodels.OverviewModelFactory
import com.example.watcher.ui.viewmodels.OverviewViewModel
import com.example.watcher.utils.Resource

class MoviesOverview : Fragment(), MovieClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoviesOverviewBinding.inflate(inflater, container, false)
        val factory = OverviewModelFactory(MoviesRepository(MoviesDatabase(requireContext())))
        val viewModel = ViewModelProvider(this, factory).get(OverviewViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = MovieAdapter(this)
        binding.adapter = adapter
        viewModel.movies.observe(
            viewLifecycleOwner,
            { response ->
                when (response) {
                    is Resource.Succes -> {
                        hideProgressBar(binding)
                        response.data?.let { movieResponse -> // not null
                            adapter.submitList(movieResponse)
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar(binding)
                        response.message?.let { message ->
                            Log.e("MoviesOverview", "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar(binding)
                    }
                }
            }
        )
        return binding.root
    }

    private fun hideProgressBar(binding: FragmentMoviesOverviewBinding) {
        binding.progressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(binding: FragmentMoviesOverviewBinding) {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onMovieClicked(movie: Result) {
        val directions = MoviesOverviewDirections.actionMoviesOverviewToMoviesDetails(movie.id)
        findNavController().navigate(directions)
    }
}
