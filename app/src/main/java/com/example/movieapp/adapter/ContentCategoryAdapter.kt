package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ContentCategoryListItemBinding

class ContentCategoryAdapter(
    private val itemList: List<String>,
    private val onCategorySelected: (String) -> Unit
) :
    RecyclerView.Adapter<ContentCategoryAdapter.PageHolder>() {

    class PageHolder(val binding: ContentCategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        val binding =
            ContentCategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PageHolder(binding)
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val item = itemList[position]
        holder.binding.contentCategoryListItemTvContentCategory.text = item
        holder.binding.contentCategoryListItemTvContentCategory.setOnClickListener {
            onCategorySelected(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
