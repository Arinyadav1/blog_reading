package com.example.blogreading.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreading.data.BlogReadingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BlogReadingViewModel(
    private val repository: BlogReadingRepositoryImpl
) : ViewModel() {

    private val _blogReadingData = MutableStateFlow<BlogReadingUiState>(BlogReadingUiState.Loading)
    val blogReadingData = _blogReadingData.asStateFlow()

    init {
        getBlog()
    }

    private fun getBlog(){
       viewModelScope.launch(Dispatchers.IO){
           runCatching {
               repository.getBlog()
           }.onSuccess { data ->
               _blogReadingData.emit(BlogReadingUiState.Success(data))
           }.onFailure { error ->
               _blogReadingData.emit(BlogReadingUiState.Error(error.message ?: "Error"))
           }
       }
    }

}