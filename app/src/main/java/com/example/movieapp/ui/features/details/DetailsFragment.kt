package com.example.movieapp.ui.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.ui.features.popular.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
private val detailsViewModel : DetailsViewModel by viewModels()
private var _binding: FragmentDetailsBinding? = null
private val binding get() = _binding!!
    val arg: DetailsFragmentArgs by navArgs()

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentDetailsBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.movieDetails.observe(viewLifecycleOwner){
            it.let {
                setMovieInfo(it.originalTitle.toString(), it.releaseDate.toString(), it.voteAverage?.toFloat() ?: 0.0f)
            }
        }
    }
    fun setMovieInfo(title:String, date:String, voteCount:Float){
        binding.apply {
            originalTitle.text = title
            releaseDate.text = date
            ratingBar.rating = voteCount
        }
    }
}