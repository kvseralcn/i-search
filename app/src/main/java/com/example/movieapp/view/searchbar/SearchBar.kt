package com.example.movieapp.view.searchbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
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
    private var binding: SearchBarBinding
    var searchBarListener: SearchBarListener? = null

    init {
        binding = SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

        if (attributeSet != null) {
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.SearchBar
            )
            try {
                if (attributes.hasValue(R.styleable.SearchBar_searchHint)) {
                    val searchHintResId =
                        attributes.getResourceId(R.styleable.SearchBar_searchHint, 0)
                    val searchHint = attributes.getString(R.styleable.SearchBar_searchHint)
                    binding.searchBarTvHint.hint = searchHint
                }
            } catch (e: Exception) {
                attributes.recycle()
            }
        }

        binding.searchBarTvHint.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchBarListener?.onSearchClick(getInputText())
                val imm =
                    v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getInputText(): String {
        return binding.searchBarTvHint.text.toString()
    }
}