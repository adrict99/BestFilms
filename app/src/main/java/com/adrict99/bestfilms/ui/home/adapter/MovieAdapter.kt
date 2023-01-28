package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.MovieElementBinding
import com.adrict99.bestfilms.domain.model.media.movie.Movie
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterInside

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
            binding.mediaRatingText.setTextColor(
                if (item.voteAverage!! >= 9.0) context.getColor(R.color.golden)
                else if (item.voteAverage >= 7.5) context.getColor(R.color.silver)
                else if (item.voteAverage >= 5.0) context.getColor(R.color.copper)
                else context.getColor(R.color.red)
            )

            binding.mediaRatingText.text = if (item.voteAverage.equals(0.0)) "?" else item.voteAverage.toString()

            binding.mediaImageView.fromUrl(
                url = item.posterPath,
                placeholder = R.drawable.ic_movie,
                scaleType = CenterInside()
            )

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