package com.example.blogreading

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.blogreading.feature.blogList.BlogReadingScreen
import com.example.blogreading.navigation.AppRoot
import com.example.blogreading.ui.theme.BlogReadingTheme

class BlogReading : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogReadingTheme {
                AppRoot()
            }
        }
    }
}