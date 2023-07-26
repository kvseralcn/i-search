package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.ContentModelDto
import com.example.movieapp.service.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    private val _data = MutableLiveData<ContentModelDto>()
    val data: LiveData<ContentModelDto> get() = _data

    init {
        //loadContents(param)
    }

    fun loadContents(param: String) {
        viewModelScope.launch {
            contentRepository.getContent(param)
                .catch {
                    Log.e("xxxx", it.toString())
                }
                .collect { contents ->
                    Log.e("xxxx", contents.toString())

                    _data.postValue(contents)
                }
        }
    }
}