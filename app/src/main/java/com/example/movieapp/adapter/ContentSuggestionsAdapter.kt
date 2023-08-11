package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ContentSuggestionsItemBinding

class ContentSuggestionsAdapter constructor(
    private var suggestionList: List<String>,
    private var onClick: (String) -> Unit
) :
    RecyclerView.Adapter<ContentSuggestionsAdapter.MusicSuggestionsHolder>() {

    class MusicSuggestionsHolder(val binding: ContentSuggestionsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MusicSuggestionsHolder {
        val binding =
            ContentSuggestionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MusicSuggestionsHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicSuggestionsHolder, position: Int) {
        val suggestion = suggestionList[position]
        holder.binding.contentSuggestionsItemTvContent.text =
            suggestion
        holder.binding.contentSuggestionsItemTvContent.setOnClickListener {
            onClick(suggestion)
        }
    }

    override fun getItemCount() = suggestionList.size
}