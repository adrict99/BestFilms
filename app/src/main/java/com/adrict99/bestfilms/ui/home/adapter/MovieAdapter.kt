package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R

import com.adrict99.bestfilms.databinding.MovieElementBinding
import com.adrict99.bestfilms.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MovieAdapter(
    private val context: Context,
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var popularMovies = mutableListOf<Movie>()

    private var selectedItem: Int? = null
    private var url = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val item = popularMovies[position]
        holder.bindItems(item)
    }

    override fun getItemCount(): Int = popularMovies.size

    fun addAllMovies(movieList: List<Movie>) {
        popularMovies.clear()
        popularMovies.addAll(movieList)
        notifyDataSetChanged()
    }

    fun addItem(item: Movie) {
        popularMovies.add(0, item)
        notifyItemInserted(0)
    }

    inner class MovieViewHolder(
        private val binding: MovieElementBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bindItems(
            item: Movie
        ) {
            binding.mediaTitleTextView.text = item.title
            binding.mediaRatingBar.rating = item.vote_average!!.toFloat()/2

            url = item.poster_path.toString()

            val uri = BuildConfig.IMAGE_BASE_URL + item.poster_path.toString()
            Glide.with(itemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_movie)
                .fitCenter()
                .transform(RoundedCorners(30))
                .into(binding.mediaImageView)

            selectedItem = item.id
        }

        override fun onClick(p0: View?) {
            selectedItem?.let { listener.onMovieClicked(it) }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(selectedMovie: Int)
    }
}