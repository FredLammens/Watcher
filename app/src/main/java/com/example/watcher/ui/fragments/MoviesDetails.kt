@file:Suppress("RedundantNullableReturnType")

package com.example.watcher.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.watcher.data.MoviesRepository
import com.example.watcher.data.local.MoviesDatabase
import com.example.watcher.databinding.FragmentMoviesDetailsBinding
import com.example.watcher.ui.viewmodels.DetailsModelFactory
import com.example.watcher.ui.viewmodels.MovieDetailViewModel

class MoviesDetails : Fragment() {

    val arguments : MoviesDetailsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMoviesDetailsBinding.inflate(inflater,container,false)
        val factory = DetailsModelFactory(MoviesRepository(MoviesDatabase(requireContext())), arguments.movieId)
        val viewModel = ViewModelProvider(this,factory).get(MovieDetailViewModel::class.java)

        viewModel.movie.observe(viewLifecycleOwner,{
            binding.movie = it
        })

        return binding.root
    }
}