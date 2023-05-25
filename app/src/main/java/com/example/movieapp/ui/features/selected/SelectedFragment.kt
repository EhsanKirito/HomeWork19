package com.example.movieapp.ui.features.selected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.databinding.FragmentSelectedBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectedFragment : Fragment() {

private var _binding: FragmentSelectedBinding? = null

private val binding get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentSelectedBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

}