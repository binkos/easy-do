package com.proxer.easydo.onboarding.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
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

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(
            pageCount = 5,
            initialPage = 0,
            initialOffscreenLimit = 2
        )

        OnboardingPager(Modifier.weight(1f), pagerState, titles)
        Spacer(modifier = Modifier.height(8.dp))
        ButtonsRow(onOnboardingFinished, pagerState, rememberCoroutineScope())
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ButtonsRow(
    onOnboardingFinished: () -> Unit,
    pagerState: PagerState,
    corutineScope: CoroutineScope
) {
    Row(modifier = Modifier.padding(16.dp)) {
        SkipButton(Modifier.weight(1f), onOnboardingFinished)
        Spacer(modifier = Modifier.width(8.dp))
        NextButton(Modifier.weight(1f), pagerState, onOnboardingFinished, corutineScope)
    }
}

@Composable
private fun SkipButton(modifier: Modifier = Modifier, onOnboardingFinished: () -> Unit) {
    OutlinedButton(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        onClick = onOnboardingFinished,
        contentPadding = PaddingValues(8.dp)
    ) { Text(text = stringResource(R.string.skip), color = MaterialTheme.colors.onSurface) }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun NextButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onOnboardingFinished: () -> Unit,
    corutineScope: CoroutineScope
) {
    Button(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        onClick = {
            if (pagerState.pageCount == (pagerState.currentPage + 1)) {
                onOnboardingFinished()
            } else {
                corutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
    ) { Text(text = stringResource(R.string.next), color = MaterialTheme.colors.surface) }
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