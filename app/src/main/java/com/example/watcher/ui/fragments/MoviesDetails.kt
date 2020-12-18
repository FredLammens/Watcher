package com.example.watcher.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.watcher.R
import com.example.watcher.databinding.FragmentMoviesDetailsBinding
import com.example.watcher.ui.viewmodels.MovieDetailViewModel

class MoviesDetails : Fragment() {

    val arguments : MoviesDetailsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMoviesDetailsBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        viewModel.movie.observe(viewLifecycleOwner,{
            binding.movie
        })

        viewModel.updateMovie(arguments.movieId) //parking object meegeven aan viewmodel gehaald uit arguments
        return binding.root
    }
}