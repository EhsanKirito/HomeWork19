package com.example.movieapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var navController: NavController
private var _binding: FragmentMainBinding? = null

private val binding get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavInitiator()
    }

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
    private fun bottomNavInitiator() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)
    }

}