package com.example.blogreading.feature.webView

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.blogreading.navigation.WebViewBlogRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WebViewViewModel(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val route = stateHandle.toRoute<WebViewBlogRoute>().url
    private val _url = MutableStateFlow(route)
    val url = _url.asStateFlow()

    init {
        Log.d("WebViewVM", "ViewModel created. URL = $route")

        viewModelScope.launch {
            url.collect {
                Log.d("WebViewVM", "URL updated: $it")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("WebViewVM", "ViewModel destroyed")
    }

}