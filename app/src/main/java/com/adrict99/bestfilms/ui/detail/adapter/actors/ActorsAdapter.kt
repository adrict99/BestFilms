package com.adrict99.bestfilms.ui.detail.adapter.actors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.ActorItemBinding
import com.adrict99.bestfilms.domain.model.detail.Cast
import com.adrict99.bestfilms.utils.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class ActorsAdapter(
    private val context: Context,
    private val listener: OnActorsClickListener
) : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    private var actorsList = mutableListOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val binding = ActorItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ActorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) =
        holder.bindItem(actorsList[position])

    override fun getItemCount(): Int = actorsList.size

    fun addAllActors(list: List<Cast>) {
        actorsList.clear()
        actorsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ActorsViewHolder(
        private val binding: ActorItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bindItem(item: Cast) {
            binding.actorItemImageView.fromUrl(
                url = item.profilePath,
                roundedCorners = false,
                circleCrop = true,
                placeholder = R.drawable.ic_movie,
                scaleType = CenterCrop()
            )
        }

        override fun onClick(v: View?) = listener.onActorClicked(actorsList)
    }

    interface OnActorsClickListener {
        fun onActorClicked(selectedActor: MutableList<Cast>)
    }

}