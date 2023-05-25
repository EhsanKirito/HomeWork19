package com.example.movieapp.ui.features.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {

private var _binding: FragmentPopularBinding? = null
private val popularViewModel : PopularViewModel by viewModels()
private val binding get() = _binding!!
private lateinit var recyclerView:RecyclerView
private lateinit var adaptor: PopularAdaptor

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentPopularBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recyclerView = binding.recyclerView
    recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    adaptor = PopularAdaptor({},{},{})
    recyclerView.adapter = adaptor
    binding.searchMoviesView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            popularViewModel.defaultSearchText(p0?: " ")
            return true
        }
    })
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val itemPosition = layoutManager.findLastVisibleItemPosition()
            if (itemPosition == adaptor.itemCount-1){
                popularViewModel.nextPage()
            }
        }
    })
    popularViewModel.searchMovies.observe(viewLifecycleOwner){
        Log.e("TAG", "onViewCreated: ${it.size}" )
        adaptor.submitList(it)
    }
}

}