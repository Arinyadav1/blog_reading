package com.example.blogreading.feature.blogList

import com.example.blogreading.model.PostResponse

sealed interface BlogListUiState {
    data object Loading : BlogListUiState
    data class Error(val msg : String) : BlogListUiState
    data class Success(val data : List<PostResponse>) : BlogListUiState
}