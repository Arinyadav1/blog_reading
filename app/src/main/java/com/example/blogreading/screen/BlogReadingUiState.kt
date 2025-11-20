package com.example.blogreading.screen

import com.example.blogreading.model.PostResponse

sealed interface BlogReadingUiState {
    data object Loading : BlogReadingUiState
    data class Error(val msg : String) : BlogReadingUiState
    data class Success(val data : List<PostResponse>) : BlogReadingUiState
}