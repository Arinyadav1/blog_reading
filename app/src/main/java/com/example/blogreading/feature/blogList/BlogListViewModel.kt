package com.example.blogreading.feature.blogList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreading.data.BlogReadingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val repository: BlogReadingRepositoryImpl
) : ViewModel() {

    var blogReadingData = repository.getBlog()

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    fun retry() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefresh.emit(true)
            blogReadingData = repository.getBlog()
            _isRefresh.emit(false)
        }
    }

}