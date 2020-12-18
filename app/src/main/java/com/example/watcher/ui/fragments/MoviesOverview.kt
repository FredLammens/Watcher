package com.example.watcher.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.watcher.R
import com.example.watcher.data.remote.TMDBApiService
import com.example.watcher.databinding.FragmentMoviesOverviewBinding
import com.example.watcher.models.movies.Result
import com.example.watcher.ui.adapters.MovieAdapter
import com.example.watcher.ui.adapters.MovieClickListener
import com.example.watcher.ui.viewmodels.OverviewModelFactory
import com.example.watcher.ui.viewmodels.OverviewViewModel

class MoviesOverview : Fragment(), MovieClickListener {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoviesOverviewBinding.inflate(inflater,container,false)
        val factory = OverviewModelFactory(TMDBApiService())
        val viewModel = ViewModelProvider(this,factory).get(OverviewViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = MovieAdapter(this)
        binding.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        return binding.root
    }

    override fun onMovieClicked(movie: Result) {
        val directions = MoviesOverviewDirections.actionMoviesOverviewToMoviesDetails(movie.id)
        findNavController().navigate(directions)
    }
}