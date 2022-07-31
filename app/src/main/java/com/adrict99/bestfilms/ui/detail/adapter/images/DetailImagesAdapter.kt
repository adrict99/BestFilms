package com.adrict99.bestfilms.ui.detail.adapter.images

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.databinding.ImageItemBinding

class DetailImagesAdapter(
    private val context: Context,
    private val onItemClick: (ImageView) -> Unit
) : RecyclerView.Adapter<DetailImagesViewHolder>() {

    val imagesList: MutableList<ImageView> = mutableListOf()

    override fun getItemCount(): Int = imagesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImagesViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailImagesViewHolder, position: Int) {
        val item = imagesList[position]
        holder.bind(item, onItemClick)
    }

    fun addAll(list: List<ImageView>) {
        imagesList.clear()
        imagesList.addAll(list)
        notifyDataSetChanged()
    }

}