package com.example.movieapp.ui.features.comingsoon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.movieapp.databinding.FragmentComingSoonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComingSoonFragment : Fragment() {
private var _binding: FragmentComingSoonBinding? = null

private val binding get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentComingSoonBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity?.startForegroundService(Intent(requireContext(), ExampleService::class.java))
            }else{

            activity?.startService(Intent(requireContext(), ExampleService::class.java))
        }

        }
//        binding.btnStop.setOnClickListener{
//            activity?.stopService(Intent(requireContext(), ExampleService::class.java))
//        }

    }

}