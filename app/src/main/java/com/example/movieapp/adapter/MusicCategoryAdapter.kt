package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.ContentResultModel
import com.example.movieapp.databinding.MusicCategoryListItemBinding

class MusicCategoryAdapter(
    private val itemList: List<String>,
    private val onItemClick: (ContentResultModel) -> Unit
) :
    RecyclerView.Adapter<MusicCategoryAdapter.PageHolder>() {

    class PageHolder(val binding: MusicCategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        val binding =
            MusicCategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicCategoryAdapter.PageHolder(binding)
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val item = itemList[position]
        holder.binding.musicCategoryListItemTvMusicCategory.text = item
        holder.binding.musicCategoryListItemTvMusicCategory.setOnClickListener {
            //TODO bu categoriye ait parçaları listele
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
