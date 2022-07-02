package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R

import com.adrict99.bestfilms.databinding.MovieElementBinding
import com.adrict99.bestfilms.domain.model.media.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MovieAdapter(
    private val context: Context,
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var popularMovies = mutableListOf<Movie>()

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
        holder.bindItems(popularMovies[position])
    }

    override fun getItemCount(): Int = popularMovies.size

    fun addAllMovies(movieList: List<Movie>) {
        popularMovies.clear()
        popularMovies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val binding: MovieElementBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bindItems(
            item: Movie
        ) {
            if (item.voteAverage!! >= 9) {
                binding.mediaRatingText.setTextColor(context.getColor(R.color.golden))
            } else if (item.voteAverage >= 7.5) {
                binding.mediaRatingText.setTextColor(context.getColor(R.color.silver))
            } else if (item.voteAverage >= 5) {
                binding.mediaRatingText.setTextColor(context.getColor(R.color.copper))
            } else {
                binding.mediaRatingText.setTextColor(context.getColor(R.color.red))
            }
            binding.mediaRatingText.text = item.voteAverage.toString()

            val uri = BuildConfig.IMAGE_BASE_URL + item.poster_path.toString()
            Glide.with(itemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_movie)
                .fitCenter()
                .transform(RoundedCorners(30))
                .into(binding.mediaImageView)

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onMovieClicked(popularMovies[this.layoutPosition].id)
        }
    }

    interface OnMovieClickListener {
        fun onMovieClicked(selectedMovie: Int?)
    }
}