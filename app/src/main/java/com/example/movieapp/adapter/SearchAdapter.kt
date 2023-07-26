package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.databinding.SearchListItemBinding
import com.example.movieapp.model.ContentResultModel

class SearchAdapter constructor(
    private val searchList: List<ContentResultModel>,
    private val onItemClick: (ContentResultModel) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.PageHolder>() {

    class PageHolder(val binding: SearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PageHolder {
        val binding =
            SearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PageHolder(binding)
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val contentResult = searchList[position]
        Glide.with(holder.itemView.context)
            .load(contentResult.artworkUrl100)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.searchListItemIvCover)

        holder.binding.searchListItemTvArtistName.text = contentResult.artistName.toString()
        holder.binding.searchListItemTvCollectionName.text =
            "${contentResult.trackName} - ${contentResult.collectionName}"
        holder.binding.searchListItemPlayButton.setOnClickListener {
            onItemClick(contentResult)
        }
    }

    override fun getItemCount() = searchList.size
}