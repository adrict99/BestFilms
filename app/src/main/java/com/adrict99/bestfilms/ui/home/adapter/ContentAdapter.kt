package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.AllContentElementBinding
import com.adrict99.bestfilms.domain.model.media.TrendingContent
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.FitCenter

class ContentAdapter(
    private val context: Context,
    private val listener: OnContentClickListener
) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private var popularAll = mutableListOf<TrendingContent>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentViewHolder {
        val binding = AllContentElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentAdapter.ContentViewHolder, position: Int) {
        holder.bindItems(popularAll[position])
    }

    override fun getItemCount(): Int = popularAll.size

    fun addAllContent(contentList: List<TrendingContent>) {
        popularAll.clear()
        popularAll.addAll(contentList)
        notifyDataSetChanged()
    }

    inner class ContentViewHolder(
        private val binding: AllContentElementBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bindItems(
            item: TrendingContent
        ) {
            binding.allContentRatingText.setTextColor(
                if (item.voteAverage!! >= 9.0) context.getColor(R.color.golden)
                else if (item.voteAverage >= 7.5) context.getColor(R.color.silver)
                else if (item.voteAverage >= 5.0) context.getColor(R.color.copper)
                else context.getColor(R.color.red)
            )

            binding.allContentRatingText.text = if (item.voteAverage.equals(0.0)) "?" else item.voteAverage.toString()

            binding.allContentImageView.fromUrl(
                url = item.posterPath,
                roundedCorners = true,
                placeholder = R.drawable.ic_movie,
                scaleType = FitCenter()
            )

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onContentClicked(popularAll[this.layoutPosition].id)
        }
    }

    interface OnContentClickListener {
        fun onContentClicked(selectedContent: Int?)
    }
}