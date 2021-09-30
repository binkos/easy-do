package com.proxer.easydo.main.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.proxer.easydo.main.R
import com.proxer.easydo.main.ui.MainEvent
import com.proxer.easydo.main.ui.MainState
import com.proxer.easydo.main.ui.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel, finishActivity: () -> Unit) {

    val state by viewModel.state.collectAsState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        when {
            !state.isDrawerClosed -> viewModel.sendEvent(MainEvent.ClickOnDrawer)
            bottomSheetScaffoldState.bottomSheetState.isExpanded ->
                coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
            else -> finishActivity()
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { BottomSheet { coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() } } },
        sheetPeekHeight = 0.dp
    ) {
        BoxWithConstraints(modifier = Modifier.background(MaterialTheme.colors.primary)) {
            val constraintsScope = this
            val widthDp = with(LocalDensity.current) { constraintsScope.maxWidth }

            MainBody(
                Modifier
                    .zIndex(1f)
                    .padding(vertical = animateDpAsState(targetValue = if (state.isDrawerClosed) 0.dp else 16.dp).value)
                    .offset(
                        x = animateDpAsState(
                            targetValue = if (state.isDrawerClosed) 0.dp else widthDp.times(0.7f)
                        ).value
                    )
                    .clip(
                        RoundedCornerShape(
                            animateDpAsState(targetValue = (if (state.isDrawerClosed) 0 else 16).dp).value
                        )
                    )
                    .fillMaxSize(),
                state,
                viewModel,
                onAddButtonPressed = {
                    coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                })

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
}

@Composable
private fun MainBody(
    modifier: Modifier,
    state: MainState,
    viewModel: MainViewModel,
    onAddButtonPressed: () -> Unit
) {
    Column(modifier = modifier.background(MaterialTheme.colors.surface)) {
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
        NavigationSheet(
            onAddButtonPressed = { onAddButtonPressed() },
            onMenuOpenChanged = { viewModel.sendEvent(MainEvent.ClickOnDrawer) }
        )
    }
}