package com.example.blogreading.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import com.example.blogreading.model.PostResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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
        ) {
            when (uiState) {
                is BlogReadingUiState.Error -> {
                    Box(
                        modifier
                            .fillMaxSize()
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ShowListOfBlog(
    blogData: List<PostResponse>,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val imageLoader =
        ImageLoader.Builder(context).crossfade(true).diskCachePolicy(CachePolicy.ENABLED)
            .build()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(blogData) { index, item ->
            Column {
                ElevatedCard(
                    modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {

                            }
                        ),
                    shape = RectangleShape,
                ) {
                    SubcomposeAsyncImage(
                        model = item.featuredMediaUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.Crop,
                        imageLoader = imageLoader,
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                )
                            }
                        },
                    )
                    Spacer(
                        modifier
                            .height(8.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        modifier = modifier.padding(horizontal = 12.dp),
                        text = item.title.rendered,
                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier
                            .height(12.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        modifier = modifier.padding(start = 12.dp),
                        text = LocalDateTime.parse(item.date)
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        fontSize = 8.sp
                    )

                    Spacer(
                        modifier
                            .height(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
