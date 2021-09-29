package com.proxer.easydo.main.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.proxer.easydo.main.R
import com.proxer.easydo.main.ui.MainEvent
import com.proxer.easydo.main.ui.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val state by viewModel.state.collectAsState()
    BoxWithConstraints(modifier = Modifier.background(MaterialTheme.colors.primary)) {
        val constraintsScope = this
        val widthDp = with(LocalDensity.current) { constraintsScope.maxWidth }
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(vertical = animateDpAsState(targetValue = if (state.isDrawerClosed) 0.dp else 16.dp).value)
                .offset(
                    x = animateDpAsState(
                        targetValue = if (state.isDrawerClosed) 0.dp else widthDp.times(0.7f)
                    ).value
                )
                .clip(RoundedCornerShape(animateDpAsState(targetValue = (if (state.isDrawerClosed) 0 else 16).dp).value))
                .background(MaterialTheme.colors.surface)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.categories).uppercase()
            )

            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item { Spacer(Modifier) }
                items(state.categories) { Category(it) }
                item { Spacer(Modifier) }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.todays_tasks).uppercase()
            )

            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                items(state.tasks) {
                    Task(it) { task -> viewModel.sendEvent(MainEvent.CompleteTask(task)) }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            NavigationSheet { viewModel.sendEvent(MainEvent.ClickOnDrawer) }
        }

        Box(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
                .zIndex(0f)
                .fillMaxWidth(0.60f)
                .fillMaxHeight()
        ) {
            DrawerMenu { viewModel.sendEvent(MainEvent.ClickOnDrawer) }
        }
    }
}