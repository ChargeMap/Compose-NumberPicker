package com.chargemap.compose.numberpicker

import androidx.compose.foundation.layout.Row
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kotlin.math.abs

sealed interface Hours {
    val hours: Int
    val minutes: Int
}

data class FullHours(
    override val hours: Int,
    override val minutes: Int,
) : Hours

data class AMPMHours(
    override val hours: Int,
    override val minutes: Int,
    val dayTime: DayTime
) : Hours {
    enum class DayTime {
        AM,
        PM;
    }
}

@Composable
fun HoursNumberPicker(
    modifier: Modifier = Modifier,
    value: Hours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int> = when (value) {
        is FullHours -> (0..23)
        is AMPMHours -> (1..12)
    },
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
) {
    when (value) {
        is FullHours ->
            FullHoursNumberPicker(
                modifier = modifier,
                value = value,
                leadingZero = leadingZero,
                hoursRange = hoursRange,
                minutesRange = minutesRange,
                hoursDivider = hoursDivider,
                minutesDivider = minutesDivider,
                onValueChange = onValueChange,
                dividersColor = dividersColor,
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
            )
        is AMPMHours ->
            AMPMHoursNumberPicker(
                modifier = modifier,
                value = value,
                leadingZero = leadingZero,
                hoursRange = hoursRange,
                minutesRange = minutesRange,
                hoursDivider = hoursDivider,
                minutesDivider = minutesDivider,
                onValueChange = onValueChange,
                dividersColor = dividersColor,
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
            )
    }
}

@Composable
fun FullHoursNumberPicker(
    modifier: Modifier = Modifier,
    value: FullHours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int>,
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.hours,
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()
    }
}

@Composable
fun AMPMHoursNumberPicker(
    modifier: Modifier = Modifier,
    value: AMPMHours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int>,
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            modifier = Modifier.weight(1f),
            value = value.hours,
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()

        NumberPicker(
            value = when (value.dayTime) {
                AMPMHours.DayTime.AM -> 0
                else -> 1
            },
            label = {
                when (it) {
                    0 -> "AM"
                    else -> "PM"
                }
            },
            onValueChange = {
                onValueChange(
                    value.copy(
                        dayTime = when (it) {
                            0 -> AMPMHours.DayTime.AM
                            else -> AMPMHours.DayTime.PM
                        }
                    )
                )
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            selectedTextStyle = selectedTextStyle,
            range = (0..1)
        )
    }
}
