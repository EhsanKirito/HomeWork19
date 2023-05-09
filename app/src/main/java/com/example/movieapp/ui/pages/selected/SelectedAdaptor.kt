package com.example.movieapp.ui.pages.selected

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.model.ui.MovieItem
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.util.diffutil.MovieCallBack

class SelectedAdaptor (
    private val onclick: (Int) -> Unit,
    private val onLikeClicked: (MovieItem) -> Unit,
    private val onLongClickedListener: (MovieItem) -> Unit,
) :
    ListAdapter<MovieItem, SelectedAdaptor.ViewHolder>(MovieCallBack()) {
    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener { getItem(adapterPosition).id?.let { id -> onclick(id) } }
                setOnLongClickListener {
                    onLongClickedListener(getItem(adapterPosition))
                    true
                }
            }
        }

        fun bind(item: MovieItem) {
            with(binding) {
                tvItemTitle.text = item.title
                Glide.with(binding.root).load(item.posterPath).into(itemMoviePoster)
                imgLike.apply {
                    setOnClickListener { onLikeClicked(getItem(adapterPosition)) }
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}