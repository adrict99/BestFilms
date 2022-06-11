package com.adrict99.bestfilms.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.BuildConfig
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.AllContentElementBinding
import com.adrict99.bestfilms.domain.model.TrendingContent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ContentAdapter(
    private val context: Context,
    private val listener: OnContentClickListener
) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private var popularAll = mutableListOf<TrendingContent>()

    private var selectedItem: Int? = null
    private var url = ""

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
        val item = popularAll[position]
        holder.bindItems(item)
    }

    override fun getItemCount(): Int = popularAll.size

    fun addAllContent(contentList: List<TrendingContent>) {
        popularAll.clear()
        popularAll.addAll(contentList)
        notifyDataSetChanged()
    }

    fun addItem(item: TrendingContent) {
        popularAll.add(0, item)
        notifyItemInserted(0)
    }

    inner class ContentViewHolder(
        private val binding: AllContentElementBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bindItems(
            item: TrendingContent
        ) {
            binding.allContentTitleTextView.text = item.title
            binding.allContentRatingBar.rating = item.voteAverage!!.toFloat()/2

            url = item.posterPath.toString()

            val uri = BuildConfig.IMAGE_BASE_URL + item.posterPath.toString()
            Glide.with(itemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_movie)
                .fitCenter()
                .transform(RoundedCorners(30))
                .into(binding.allContentImageView)

            selectedItem = item.id
        }

        override fun onClick(p0: View?) {
            selectedItem?.let { listener.onContentClicked(it) }
        }
    }

    interface OnContentClickListener {
        fun onContentClicked(selectedContent: Int)
    }
}