package com.example.blogreading.feature.blogList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.blogreading.data.PostsDatabaseRepository
import com.example.blogreading.data.PostsRepository
import com.example.blogreading.data.PostsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val repository: PostsRepository,
) : ViewModel() {

    var blogReadingData = repository.getBlog().cachedIn(viewModelScope)

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