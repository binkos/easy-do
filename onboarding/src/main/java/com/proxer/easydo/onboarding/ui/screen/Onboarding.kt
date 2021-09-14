package com.proxer.easydo.onboarding.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.proxer.easydo.onboarding.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(onOnboardingFinished: () -> Unit) {
    val titles = listOf(
        stringResource(R.string.create_simple_tasks),
        stringResource(R.string.plan_your_day),
        stringResource(R.string.plan_your_every_day_activity),
        stringResource(R.string.add_notifications),
        stringResource(R.string.see_progress)
    )
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(
            pageCount = titles.size,
            initialPage = 0,
            initialOffscreenLimit = 2
        )

        Text(
            text = stringResource(R.string.skip),
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .clickable { onOnboardingFinished() }
                .align(Alignment.End)
        )
        OnboardingPager(Modifier.weight(1f), pagerState, titles)
        Spacer(modifier = Modifier.height(8.dp))
        NextCircleButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp),
            pagerState
        ) {
            if (pagerState.pageCount == (pagerState.currentPage + 1)) {
                onOnboardingFinished()
            } else {
                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnboardingPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    titles: List<String>
) {
    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = pagerState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
        itemSpacing = 4.dp
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = titles[it],
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun NextCircleButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    clickListener: () -> Unit
) {
    val oneSegmentPart = remember { 360f / pagerState.pageCount }
    val finalCurrentPage = pagerState.currentPage + 1
    val multiply = oneSegmentPart * pagerState.currentPageOffset

    val angelState =
        animateFloatAsState(targetValue = oneSegmentPart * finalCurrentPage + multiply)

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .size(48.dp)
                .clip(CircleShape)
                .clickable { clickListener() }
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(24.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                painter = painterResource(id = R.drawable.ic_right_arrow)
            )
        }

        Canvas(modifier = Modifier.size(64.dp)) {
            drawCircle(
                SolidColor(Color(0xFF2F4951)),
                style = Stroke(8f)
            )
            drawArc(
                brush = SolidColor(Color.Green),
                startAngle = -90f,
                sweepAngle = angelState.value,
                useCenter = false,
                style = Stroke(8f)
            )
        }
    }
}
