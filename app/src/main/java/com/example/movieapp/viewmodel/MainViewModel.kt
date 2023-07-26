package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val contentRepository: ContentRepository
) : ViewModel() {

    /*  private val _data = MutableLiveData<ContentModelDto>()
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
      }*/

}