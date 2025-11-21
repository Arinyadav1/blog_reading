package com.example.blogreading.feature.webView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.blogreading.navigation.WebViewBlogRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WebViewViewModel(
    stateHandle: SavedStateHandle
) : ViewModel() {
    private val route = stateHandle.toRoute<WebViewBlogRoute>().url
    private val _url = MutableStateFlow(route)
    val url = _url.asStateFlow()
}