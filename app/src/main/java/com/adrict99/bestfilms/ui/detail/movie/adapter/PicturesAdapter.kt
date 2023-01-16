package com.adrict99.bestfilms.ui.detail.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.PictureItemBinding
import com.adrict99.bestfilms.domain.model.Picture
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class PicturesAdapter(
    private val context: Context,
    private val listener: OnPictureClickListener
) : RecyclerView.Adapter<PicturesAdapter.PicturesViewHolder>() {

    private var picturesList = mutableListOf<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val binding = PictureItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return PicturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) =
        holder.bindItem(picturesList[position])

    override fun getItemCount(): Int = picturesList.size

    fun addAllPictures(list: List<Picture>) {
        picturesList.clear()
        picturesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class PicturesViewHolder(
        private val binding: PictureItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bindItem(item: Picture) {
            binding.pictureItemImageView.fromUrl(
                url = item.url,
                placeholder = R.drawable.ic_movie,
                scaleType = CenterCrop()
            )
        }

        override fun onClick(v: View?) {
            listener.onPictureClicked(picturesList[this.layoutPosition])
        }
    }

    interface OnPictureClickListener {
        fun onPictureClicked(selectedPicture: Picture)
    }

}