package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.R
import com.example.movieapp.data.ContentResultModel
import com.example.movieapp.databinding.SearchListItemBinding

class SearchAdapter constructor(
    private val searchList: List<ContentResultModel>,
    private val onReplayClick: () -> Unit,
    private val onItemClick: (ContentResultModel, Int) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.PageHolder>() {

    private var isPlaying: Boolean = false

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
        val context = holder.itemView.context

        Glide.with(holder.itemView.context)
            .load(contentResult.artworkUrl100)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.searchListItemIvCover)

        holder.binding.searchListItemTvArtistName.text = contentResult.artistName.toString()
        holder.binding.searchListItemTvCollectionName.text =
            "${contentResult.trackName} - ${contentResult.collectionName}"

        holder.binding.searchListItemPlayButton.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                if (isPlaying) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
            )
        )

        holder.binding.searchListItemPlayButton.setOnClickListener {
            isPlaying = !isPlaying
            onItemClick(contentResult, position)
            //TODO müzik bittiğinde buton iconunu "baseline_play_arrow_24" iconuna çevir tekrar
        }

        holder.binding.searchListItemReplayButton.alpha = if (isPlaying) 1f else 0.25f
        holder.binding.searchListItemReplayButton.isEnabled = isPlaying
        holder.binding.searchListItemReplayButton.setOnClickListener {
            onReplayClick()
        }
    }

    override fun getItemCount() = searchList.size
}