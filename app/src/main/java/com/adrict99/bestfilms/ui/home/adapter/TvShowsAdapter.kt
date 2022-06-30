package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.TvShowElementBinding
import com.adrict99.bestfilms.domain.model.media.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class TvShowsAdapter(
    private val context: Context,
    private val listener: OnTvShowClickListener
) : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private var popularTvShows = mutableListOf<TvShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): TvShowsViewHolder {
        val binding = TvShowElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return TvShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        holder.bindItems(popularTvShows[position])
    }

    override fun getItemCount(): Int = popularTvShows.size

    fun addAllTvShows(tvShowList: List<TvShow>) {
        popularTvShows.clear()
        popularTvShows.addAll(tvShowList)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(
        private val binding: TvShowElementBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bindItems(
            item: TvShow
        ) {
            binding.tvShowTitleTextView.text = item.name
            binding.tvShowRatingBar.rating = item.voteAverage!!.toFloat()/2

            val uri = BuildConfig.IMAGE_BASE_URL + item.posterPath.toString()
            Glide.with(itemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_movie)
                .fitCenter()
                .transform(RoundedCorners(30))
                .into(binding.tvShowImageView)

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onTvShowClicked(popularTvShows[this.layoutPosition].id)
        }
    }

    interface OnTvShowClickListener {
        fun onTvShowClicked(selectedTvShow: Int?)
    }
}