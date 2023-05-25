package com.example.movieapp.ui.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.util.convertor.toMovieDetailsItemFragment
import dagger.hilt.android.AndroidEntryPoint

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

        detailsViewModel.movieDetails.observe(viewLifecycleOwner) { movieDetailsItem ->
            setToUi(movieDetailsItem)
        }

    }

    private fun setToUi(movieDetailsItem: MovieDetailsItem) {
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