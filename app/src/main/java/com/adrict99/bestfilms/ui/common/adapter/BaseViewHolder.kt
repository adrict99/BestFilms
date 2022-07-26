package com.adrict99.bestfilms.ui.common.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}