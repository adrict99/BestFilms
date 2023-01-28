package com.adrict99.bestfilms.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.ItemResultBinding
import com.adrict99.bestfilms.domain.model.search.PresentationSearchResult
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterInside

class ResultAdapter(
    private val context: Context,
    private val listener: OnResultClickListener
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private var resultsList = mutableListOf<PresentationSearchResult>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultAdapter.ResultViewHolder {
        val binding = ItemResultBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ResultViewHolder, position: Int) {
        holder.bindItem(resultsList[position])
    }

    override fun getItemCount(): Int = resultsList.size

    fun addAllResults(results: List<PresentationSearchResult>) {
        resultsList.clear()
        resultsList.addAll(results)
        notifyDataSetChanged()
    }

    inner class ResultViewHolder(
        private val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
            fun bindItem(
                item: PresentationSearchResult
            ) {
                binding.apply {
                    itemResultImageView.fromUrl(
                        url = item.imageUrl,
                        placeholder = R.drawable.ic_image,
                        scaleType = CenterInside()
                    )
                    itemResultTextView.text = item.title
                    itemResultOriginalTextView.text = item.originalTitle
                    itemResultTypeTextView.text = item.type
                }
            }

        override fun onClick(v: View?) {
            listener.onResultClicked(resultsList[this.layoutPosition])
        }
    }

    interface OnResultClickListener {
        fun onResultClicked(result: PresentationSearchResult)
    }

}