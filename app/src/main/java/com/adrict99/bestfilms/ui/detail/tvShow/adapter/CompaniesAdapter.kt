package com.adrict99.bestfilms.ui.detail.tvShow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrict99.bestfilms.R
import com.adrict99.bestfilms.databinding.CompanyItemBinding
import com.adrict99.bestfilms.domain.model.media.tvShow.presentation.PresentationProductionCompany
import com.adrict99.bestfilms.utils.extensions.fromUrl
import com.bumptech.glide.load.resource.bitmap.CenterInside

class CompaniesAdapter(
    private val context: Context,
) : RecyclerView.Adapter<CompaniesAdapter.CompanyViewHolder>() {

    private var companiesList = mutableListOf<PresentationProductionCompany>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = CompanyItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) =
        holder.bindItem(companiesList[position])

    override fun getItemCount(): Int = companiesList.size

    fun addAllCompanies(list: List<PresentationProductionCompany>) {
        companiesList.clear()
        companiesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CompanyViewHolder(
        private val binding: CompanyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: PresentationProductionCompany) {
            binding.apply {
                companyItemTextView.text = item.name
                companyItemImageView.fromUrl(
                    url = item.imageUrl,
                    roundedCorners = false,
                    placeholder = R.drawable.ic_business,
                    scaleType = CenterInside()
                )
            }
        }
    }

}