package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R

import com.adrict99.bestfilms.databinding.MediaElementBinding
import com.adrict99.bestfilms.domain.model.Movie
import com.adrict99.bestfilms.domain.model.TrendingContent
import com.adrict99.bestfilms.domain.model.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class HomeAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var popularAll = mutableListOf<TrendingContent>()
    private var popularMovies = mutableListOf<Movie>()
    private var popularTvShows = mutableListOf<TvShow>()

    private var selectedItem: Int? = null
    private var url = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): HomeViewHolder {
        val binding = MediaElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val item = popularMovies[position]
        holder.bindItems(item)
    }

    override fun getItemCount(): Int = popularMovies.size

    fun addAllMovies(movieList: List<Movie>) {
        popularMovies.clear()
        popularMovies.addAll(movieList)
        notifyDataSetChanged()
    }

    fun addAllContent(contentList: List<TrendingContent>) {
        popularAll.clear()
        popularAll.addAll(contentList)
        notifyDataSetChanged()
    }

    fun addAllTvShows(tvShowList: List<TvShow>) {
        popularTvShows.clear()
        popularTvShows.addAll(tvShowList)
        notifyDataSetChanged()
    }

    fun addItem(item: Movie) {
        popularMovies.add(0, item)
        notifyItemInserted(0)
    }

    inner class HomeViewHolder(
        private val binding: MediaElementBinding
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
            selectedItem?.let { listener.onItemClicked(it) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(selectedItem: Int)
    }
}