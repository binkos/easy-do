package com.proxer.easydo.main.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.proxer.easydo.main.R
import com.proxer.easydo.main.ui.CreateTaskEvent
import com.proxer.easydo.main.ui.MainViewModel
import com.proxer.easydo.main.ui.TaskCategory
import com.proxer.easydo.main.ui.model.TaskModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheet(
    viewModel: MainViewModel,
    onCrossPressed: () -> Unit
) {
    val categories = viewModel.createTaskState.collectAsState()
    val materialDialogState = rememberMaterialDialogState()
    val dateState = remember { mutableStateOf("Select Date") }
    var taskText by remember { mutableStateOf(" ") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth(0.15f)
                .height(4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            drawRoundRect(
                color = Color.LightGray,
                topLeft = Offset.Zero,
                cornerRadius = CornerRadius(4f),
                size = Size(size.width, size.height)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.create_task)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(R.string.task_title)
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            value = taskText,
            onValueChange = { taskText = it }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.Start),
            text = stringResource(R.string.select_category)
        )


        Spacer(modifier = Modifier.height(6.dp))
        FlowRow(
            modifier = Modifier.padding(horizontal = 8.dp),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp
        ) {
            categories.value.categories.forEach {
                OutlinedSelectCategory(it) { category ->
                    viewModel.sendCreateTaskEvent(CreateTaskEvent.SelectCategory(category))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.Start),
            text = stringResource(R.string.date_and_time)
        )
        Spacer(modifier = Modifier.height(4.dp))
        DatePickerPreview(textState = dateState) { materialDialogState.show() }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onClick = {
                val category = categories.value.categories.first { it.isSelected }
                viewModel.sendCreateTaskEvent(
                    CreateTaskEvent.CreateTask(
                        TaskModel(0, taskText, category.color, false, false, category.id)
                    )
                )
            }) {
            Text(text = stringResource(R.string.done))
        }

        Spacer(modifier = Modifier.height(16.dp))

        DatePicker(dialogState = materialDialogState) { dateState.value = it.toString() }
    }
}

@Composable
private fun OutlinedSelectCategory(taskCategory: TaskCategory, onSelect: (TaskCategory) -> Unit) {
    val boxDp = animateDpAsState(targetValue = if (taskCategory.isSelected) 20.dp else 0.dp)

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onSelect(taskCategory) }
            .border(width = 1.dp, color = Color.Green, shape = CircleShape)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(boxDp.value)) {
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.ic_check_vote),
                contentDescription = null
            )
        }
        Text(text = taskCategory.name)
    }
}

@Composable
fun DatePickerPreview(textState: State<String>, onClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClicked() }
            .padding(horizontal = 8.dp)
            .border(1.dp, color = MaterialTheme.colors.surface)
            .height(40.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = textState.value)
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null)
    }
}