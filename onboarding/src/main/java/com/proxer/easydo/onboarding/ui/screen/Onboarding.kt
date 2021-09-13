package com.proxer.easydo.onboarding.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.proxer.easydo.onboarding.R
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
    val corutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.skip),
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .clickable { onOnboardingFinished() }
                .align(Alignment.End)
        )
        val pagerState =
            rememberPagerState(pageCount = 5, initialPage = 0, initialOffscreenLimit = 2)

        OnboardingPager(Modifier.weight(1f), pagerState, titles)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(),
            onClick = {
                corutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        ) { Text(text = stringResource(R.string.next), color = MaterialTheme.colors.onSurface) }
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