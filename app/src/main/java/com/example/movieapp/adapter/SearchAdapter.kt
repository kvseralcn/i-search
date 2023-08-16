package com.example.movieapp.adapter

import android.annotation.SuppressLint
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
    private var searchList: List<ContentResultModel>,
    private val onReplayClick: () -> Unit,
    private val searchAdapterListener: SearchAdapterListener
) :
    RecyclerView.Adapter<SearchAdapter.PageHolder>() {

    interface SearchAdapterListener {
        fun onPlay(contentResultModel: ContentResultModel)
        fun onPause()
    }

    private var playingContentIndex = -1
    private var prevPlayingContentIndex = -1

    private val allItemsList = searchList

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
        val sb = StringBuilder()
        contentResult.trackName?.let { sb.append(it) }
        contentResult.collectionName?.let {
            if (sb.isNotEmpty()) sb.append(" - ")
            sb.append(it)
        }
        holder.binding.searchListItemTvCollectionName.text = sb.toString()
        holder.binding.searchListItemPlayButton.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                if (position == playingContentIndex) R.drawable.baseline_pause_24 else R.drawable.baseline_play_arrow_24
            )
        )

        holder.binding.searchListItemPlayButton.setOnClickListener {
            if (position == playingContentIndex) {
                searchAdapterListener.onPause()
                notifyItemChanged(playingContentIndex)
                playingContentIndex = -1
                prevPlayingContentIndex = -1
            } else {
                prevPlayingContentIndex = playingContentIndex
                playingContentIndex = position
                searchAdapterListener.onPlay(contentResult)
                notifyItemChanged(playingContentIndex)
            }

            if (prevPlayingContentIndex != -1)
                notifyItemChanged(prevPlayingContentIndex)
        }

        holder.binding.searchListItemReplayButton.alpha =
            if (position == playingContentIndex) 1f else 0.25f
        holder.binding.searchListItemReplayButton.isEnabled = position == playingContentIndex
        holder.binding.searchListItemReplayButton.setOnClickListener {
            onReplayClick()
        }
    }

    override fun getItemCount() = searchList.size

    @SuppressLint("NotifyDataSetChanged")
    fun filter(category: String?) {
        searchList = if (category == null) {
            allItemsList
        } else {
            allItemsList.filter {
                it.primaryGenreName == category
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}