package com.example.blogreading.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.blogreading.feature.blogList.BlogReadingScreen
import com.example.blogreading.feature.webView.WebViewScreen
import kotlinx.serialization.Serializable
import com.example.blogreading.R

@Serializable
data object ListOfBlogsRoute

@Serializable
data class WebViewBlogRoute(val url: String)

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val showBackButton = navBackStackEntry?.destination
        ?.hierarchy
        ?.any { it.hasRoute<WebViewBlogRoute>() } == true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    if (showBackButton) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
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
    this.navigate(WebViewBlogRoute(url = url))
}