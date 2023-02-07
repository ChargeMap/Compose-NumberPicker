package com.chargemap.compose.numberpicker

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    label: (Int) -> String = {
        it.toString()
    },
    value: Int,
    onValueChange: (Int) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    range: Iterable<Int>,
    textStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
) {
    ListItemPicker(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        dividersColor = dividersColor,
        list = range.toList(),
        textStyle = textStyle,
        selectedTextStyle = selectedTextStyle
    )
}
