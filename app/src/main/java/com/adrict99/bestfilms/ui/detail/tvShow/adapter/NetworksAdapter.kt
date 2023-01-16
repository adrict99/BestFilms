package com.adrict99.bestfilms.ui.detail.tvShow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.NetworkItemBinding
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationNetwork
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter

class NetworksAdapter(
    private val context: Context
) : RecyclerView.Adapter<NetworksAdapter.NetworksViewHolder>() {

    private var networksList = mutableListOf<PresentationNetwork>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworksViewHolder {
        val binding = NetworkItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return NetworksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NetworksViewHolder, position: Int) =
        holder.bindItem(networksList[position])

    override fun getItemCount(): Int = networksList.size

    fun addAllNetworks(list: List<PresentationNetwork>) {
        networksList.clear()
        networksList.addAll(list)
        notifyDataSetChanged()
    }

    inner class NetworksViewHolder(
        private val binding: NetworkItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: PresentationNetwork) {
            binding.apply {
                networkItemTextView.text = item.name
                networkItemImageView.fromUrl(
                    url = item.imageUrl,
                    roundedCorners = false,
                    placeholder = R.drawable.ic_tv,
                    scaleType = CenterInside()
                )
            }
        }
    }

}