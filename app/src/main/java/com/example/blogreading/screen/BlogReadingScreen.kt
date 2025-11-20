package com.example.blogreading.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blogreading.model.PostResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogReadingScreen(
    modifier: Modifier = Modifier,
    viewModel: BlogReadingViewModel = koinViewModel()
) {
    val uiState = viewModel.blogReadingData.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Blog Reading"
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            when (uiState) {
                is BlogReadingUiState.Error -> {
                    Box(
                        modifier.fillMaxSize()
                            .padding(horizontal = 30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.msg,
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                BlogReadingUiState.Loading -> {
                    Box(
                        modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is BlogReadingUiState.Success -> {
                    ShowListOfBlog(
                        blogData = uiState.data
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowListOfBlog(
    blogData: List<PostResponse>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize().padding(horizontal = 50.dp)
    ) {
        Text(
            text = blogData[0].id.toString()
        )
    }
}