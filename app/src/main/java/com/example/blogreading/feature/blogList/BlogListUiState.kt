package com.example.blogreading.feature.blogList

import androidx.paging.PagingData
import com.example.blogreading.model.PostResponse
import kotlinx.coroutines.flow.Flow

sealed interface BlogListUiState {
    data object Loading : BlogListUiState
    data class Error(val msg : String) : BlogListUiState
    data class Success(val data : Flow<PagingData<PostResponse>>) : BlogListUiState
}