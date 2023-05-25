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
import com.example.movieapp.util.deligation.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavInitiator()
    }
    private fun bottomNavInitiator() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.navGraphContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)
    }

}