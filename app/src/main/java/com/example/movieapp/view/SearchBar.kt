package com.example.movieapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.movieapp.R
import com.example.movieapp.databinding.SearchBarBinding

class SearchBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(
    context, attributeSet, defStyleAttr
) {
    var binding: SearchBarBinding

    init {
        binding = SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

        if (attributeSet != null) {
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.SearchBar
            )
            try {
                if (attributes.hasValue(R.styleable.SearchBar_searchHint)) {
                    val searchHintResId = attributes.getResourceId(R.styleable.SearchBar_searchHint,0)
                    val searchHint = attributes.getString(R.styleable.SearchBar_searchHint)
                    binding.searchBarTvHint.hint = searchHint
                }
            } catch (e: Exception) {
                attributes.recycle()
            }
        }

    }
}