package com.kevser.isearch.ui.search.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevser.isearch.core.service.ContentRepository
import com.kevser.isearch.data.ContentModelDto
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
    val suggestionList = listOf(
        "michael jackson",
        "billie eilish",
        "kanye west",
        "jennifer lopez",
        "adele"
    )

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