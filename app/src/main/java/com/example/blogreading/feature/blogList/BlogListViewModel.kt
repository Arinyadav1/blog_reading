package com.example.blogreading.feature.blogList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreading.data.BlogReadingRepository
import com.example.blogreading.data.BlogReadingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val repository: BlogReadingRepository
) : ViewModel() {

    private val _blogReadingData = MutableStateFlow<BlogListUiState>(BlogListUiState.Loading)
    val blogReadingData = _blogReadingData.asStateFlow()

    init {
        getBlog()
    }

    private fun getBlog(){
       viewModelScope.launch(Dispatchers.IO){
           runCatching {
               repository.getBlog()
           }.onSuccess { data ->
               _blogReadingData.emit(BlogListUiState.Success(data))
           }.onFailure { error ->
               _blogReadingData.emit(BlogListUiState.Error(error.message ?: "Error"))
           }
       }
    }

}