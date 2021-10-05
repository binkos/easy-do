package com.proxer.easydo.main.ui.component

import androidx.compose.runtime.Composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun DatePicker(
    dialogState: MaterialDialogState,
    onDateChange: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok") { dialogState.hide() }
            negativeButton("Cancel") { dialogState.hide() }
        }
    ) {
        datepicker(
            yearRange = IntRange(2021, 2100),
            onDateChange = { onDateChange(it) }
        )
    }
}