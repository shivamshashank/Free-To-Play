package com.brighterbee.tech.freetoplay.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.brighterbee.tech.freetoplay.R
import com.brighterbee.tech.freetoplay.presentation.components.NetworkImage
import com.brighterbee.tech.freetoplay.presentation.components.OnErrorIcon
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselView(
    urlsList: List<String>,
    widthFraction: Float,
) {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        val pagerState = rememberPagerState(pageCount = urlsList.size)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(widthFraction)
                .fillMaxHeight()
                .clip(shape = MaterialTheme.shapes.medium)
        ) { index ->
            NetworkImage(
                url = urlsList[index],
                crossFade = 1000,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                onLoading = {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val indicator = createRef()
                        CircularProgressIndicator(
                            modifier = Modifier.constrainAs(indicator) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                },
                onError = {
                    OnErrorIcon()
                }
            )
        }
        HorizontalPagerIndicator(
            activeColor = MaterialTheme.colors.primary,
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(color = Color.Transparent)
                .padding(bottom = 8.dp)
        )
    }
}