package com.adrict99.bestfilms.ui.detail.tvShow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.ActorItemBinding
import com.adrict99.bestfilms.databinding.SeasonItemBinding
import com.adrict99.bestfilms.domain.model.media.movie.presentation.PresentationActor
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationSeason
import com.adrict99.bestfilms.ui.detail.movie.adapter.ActorsAdapter
import com.adrict99.bestfilms.utils.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class SeasonsAdapter(
    private val context: Context,
    private val listener: OnSeasonClickListener
) : RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    private var seasonsList = mutableListOf<PresentationSeason>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val binding = SeasonItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return SeasonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) =
        holder.bindItem(seasonsList[position])

    override fun getItemCount(): Int = seasonsList.size

    fun addAllSeasons(list: List<PresentationSeason>) {
        seasonsList.clear()
        seasonsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class SeasonsViewHolder(
        private val binding: SeasonItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bindItem(item: PresentationSeason) {
            binding.apply {
                seasonItemTextView.text = item.name
                seasonItemImageView.fromUrl(
                    url = item.imageUrl,
                    roundedCorners = false,
                    circleCrop = true,
                    placeholder = R.drawable.ic_movie,
                    scaleType = CenterCrop()
                )
            }
        }

        override fun onClick(v: View?) = listener.onSeasonClicked(seasonsList)
    }

    interface OnSeasonClickListener {
        fun onSeasonClicked(selectedPresentationSeason: MutableList<PresentationSeason>)
    }

}