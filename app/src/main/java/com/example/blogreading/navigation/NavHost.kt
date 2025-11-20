package com.example.blogreading.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.blogreading.feature.blogList.BlogReadingScreen
import com.example.blogreading.feature.webView.WebViewScreen
import kotlinx.serialization.Serializable

@Serializable
data object ListOfBlogsRoute

@Serializable
data class WebViewBlogRoute(val url: String)

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Blog") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ListOfBlogsRoute
        ) {
            composable<ListOfBlogsRoute> {
                BlogReadingScreen(
                    modifier = modifier.padding(innerPadding),
                    onNavigateWebViewScreen = navController::navigateToWebViewScreen
                )
            }

            composable<WebViewBlogRoute> {
                WebViewScreen(
                    modifier = modifier.padding(innerPadding),
                )
            }
        }
    }
}

fun NavController.navigateToWebViewScreen(url: String) {
    this.navigate(WebViewBlogRoute(url))
}