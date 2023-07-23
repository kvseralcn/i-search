package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.service.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {
    // TODO: add livedata
    init {
        loadContents()
    }

    fun loadContents() {
        viewModelScope.launch {
            contentRepository.getContent()
                .catch {
                    Log.e("xxxx", it.toString())
                }
                .collect { contents ->
                    Log.e("xxxx", contents.toString())
                    // TODO: post to live data
                }
        }
    }

}