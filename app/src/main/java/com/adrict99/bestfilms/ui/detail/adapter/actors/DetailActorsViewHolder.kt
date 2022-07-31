package com.adrict99.bestfilms.ui.detail.adapter.actors

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.databinding.ActorItemBinding

class DetailActorsViewHolder(private val binding: ActorItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Actor,
        onItemClick: (Actor, ImageView) -> Unit
    ) {
        //TODO: Setup name, picture and onClickListener
    }
}