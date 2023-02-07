package com.chargemap.android.sample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.*

@Composable
fun MainActivityUI() {

    val scrollState = rememberScrollState()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    NumberPicker()
                    HoursNumberPicker1()
                    HoursNumberPicker2()
                    HoursNumberPicker3()
                    HoursNumberPicker4()
                    DoublesPicker()
                    FruitPicker()
                    IntRangePicker()
                }
            }
        }
    }
}

@Composable
private fun NumberPicker() {
    var state by remember { mutableStateOf(0) }
    NumberPicker(
        value = state,
        range = 0..10,
        onValueChange = {
            state = it
        },
        textStyle = TextStyle(),
        selectedTextStyle = TextStyle(fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun HoursNumberPicker1() {
    var state by remember { mutableStateOf<Hours>(FullHours(12, 43)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        dividersColor = MaterialTheme.colors.error,
        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.size(24.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        },
        textStyle = TextStyle(),
        selectedTextStyle = TextStyle(fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun HoursNumberPicker2() {
    var state by remember { mutableStateOf<Hours>(AMPMHours(9, 43, AMPMHours.DayTime.PM)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        dividersColor = MaterialTheme.colors.secondary,
        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.size(24.dp),
                textAlign = TextAlign.Center,
                text = ":"
            )
        },
        minutesDivider = {
            Spacer(
                modifier = Modifier.size(24.dp),
            )
        }
    )
}

@Composable
private fun HoursNumberPicker3() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        value = state,
        onValueChange = {
            state = it
        },
        minutesRange = IntProgression.fromClosedRange(0, 50, 10),
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "h"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "m"
            )
        }
    )
}

@Composable
private fun HoursNumberPicker4() {
    var state by remember { mutableStateOf<Hours>(FullHours(11, 36)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        value = state,
        onValueChange = {
            state = it
        },
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "hours"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "minutes"
            )
        }
    )
}

@Composable
private fun DoublesPicker() {
    val possibleValues = generateSequence(0.5f) { it + 0.25f }
        .takeWhile { it <= 5f }
        .toList()
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = state,
        onValueChange = { state = it },
        list = possibleValues,
        textStyle = TextStyle(),
        selectedTextStyle = TextStyle(fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun FruitPicker() {
    val possibleValues = listOf("🍎", "🍊", "🍉", "🥭", "🍈", "🍇", "🍍")
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it },
        value = state,
        onValueChange = { state = it },
        list = possibleValues
    )
}

@Composable
private fun IntRangePicker() {
    val possibleValues = (-5..10).toList()
    var value by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = value,
        onValueChange = { value = it },
        list = possibleValues
    )
}
