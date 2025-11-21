package com.example.blogreading.feature.webView

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    modifier: Modifier = Modifier,
    viewModel: WebViewViewModel = viewModel()
) {
    val url = viewModel.url.collectAsStateWithLifecycle().value

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}
