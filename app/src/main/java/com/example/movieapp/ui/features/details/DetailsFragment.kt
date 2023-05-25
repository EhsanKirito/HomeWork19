package com.example.movieapp.ui.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.util.convertor.toMovieDetailsItemFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "movieId"
    }


    private val detailsViewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.movieDetails.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "ERROOOOOOOOOOOOOOOOOOR",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBar.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBar.isInvisible = false
                                constraintLayout.isInvisible = true
                            }
                        }

                        is ResponseState.Success -> setToUi(responseState.data)
                    }
                }
            }
        }

    }

    private fun setToUi(movieDetailsItem: MovieDetailsItem) {
        binding.apply {
            progressBar.isInvisible = true
            constraintLayout.isInvisible = false
        }
        val movieDetailsFragment = movieDetailsItem.toMovieDetailsItemFragment()
        with(movieDetailsFragment) {
            binding.apply {
                tvOriginalTitle.text = title
                tvReleaseDate.text = releaseDate
                ratingBar.rating = voteAverage
            }
        }
    }
}