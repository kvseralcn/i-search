package com.example.movieapp.ui.search.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.ContentModelDto
import com.example.movieapp.core.service.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    companion object {
        const val TAG = "SearchFragmentViewModel"
    }

    private val _data = MutableLiveData<ContentModelDto>()
    val data: LiveData<ContentModelDto> get() = _data

    fun loadContents(param: String) {
        viewModelScope.launch {
            contentRepository.getContent(param)
                .catch {
                    Log.e(TAG, it.toString())
                }
                .collect { contents ->
                    Log.d(TAG, contents.toString())
                    _data.postValue(contents)
                }
        }
    }
}