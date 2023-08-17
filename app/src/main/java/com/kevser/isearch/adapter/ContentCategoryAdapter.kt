package com.kevser.isearch.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevser.isearch.R
import com.kevser.isearch.databinding.ContentCategoryListItemBinding

class ContentCategoryAdapter(
    private val itemList: List<String>,
    private val onCategorySelected: (String) -> Unit
) :
    RecyclerView.Adapter<ContentCategoryAdapter.PageHolder>() {

    var selectedCategoryIndex: Int = 0
    var previousSelectedCategory: Int = -1

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


        if (position == selectedCategoryIndex) {
            holder.binding.contentCategoryListItemTvContentCategory.setBackgroundResource(
                R.drawable.category_selected_background
            )
            holder.binding.contentCategoryListItemTvContentCategory.setTextColor(Color.parseColor("#252525"))
        } else {
            holder.binding.contentCategoryListItemTvContentCategory.setBackgroundResource(
                R.drawable.category_unselected_background
            )
            holder.binding.contentCategoryListItemTvContentCategory.setTextColor(Color.parseColor("#C8C8C8"))
        }

        holder.binding.contentCategoryListItemTvContentCategory.text = item
        holder.binding.contentCategoryListItemTvContentCategory.setOnClickListener {
            onCategorySelected(item)
            previousSelectedCategory = selectedCategoryIndex
            selectedCategoryIndex = position
            notifyItemChanged(selectedCategoryIndex)
            if (previousSelectedCategory != -1) {
                notifyItemChanged(previousSelectedCategory)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
