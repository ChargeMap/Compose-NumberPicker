import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.*

@Preview
@Composable
private fun NumberPickerPreview() {
    var state by remember { mutableStateOf(0) }
    NumberPicker(
        value = state,
        range = 0..10,
        onValueChange = {
            state = it
        },
        textStyle = TextStyle(Color.White),
        selectedTextStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold)
    )
}

@Preview
@Composable
private fun HoursNumberPicker1Preview() {
    var state by remember { mutableStateOf<Hours>(FullHours(12, 0)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        leadingZero = true,
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
        textStyle = TextStyle(Color.White),
        selectedTextStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold)
    )
}

@Preview
@Composable
private fun HoursNumberPicker2Preview() {
    var state by remember { mutableStateOf<Hours>(AMPMHours(9, 0, AMPMHours.DayTime.PM)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        leadingZero = true,
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
        },
        textStyle = TextStyle(Color.White)
    )
}

@Preview
@Composable
private fun HoursNumberPicker3Preview() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        value = state,
        onValueChange = {
            state = it
        },
        leadingZero = true,

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
        },
        textStyle = TextStyle(Color.White)
    )
}

@Preview
@Composable
private fun HoursNumberPicker4Preview() {
    var state by remember { mutableStateOf<Hours>(FullHours(11, 36)) }
    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        leadingZero = true,
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
        },
        textStyle = TextStyle(Color.White)
    )
}

@Preview
@Composable
private fun DoublesPickerPreview() {
    val possibleValues = generateSequence(0.5f) { it + 0.25f }
        .takeWhile { it <= 5f }
        .toList()
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = state,
        onValueChange = { state = it },
        list = possibleValues,
        textStyle = TextStyle(Color.White),
        selectedTextStyle = TextStyle(Color.White, fontWeight = FontWeight.Bold)
    )
}

@Preview
@Composable
private fun FruitPickerPreview() {
    val possibleValues = listOf("🍎", "🍊", "🍉", "🥭", "🍈", "🍇", "🍍")
    var state by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it },
        value = state,
        onValueChange = { state = it },
        list = possibleValues
    )
}

@Preview
@Composable
private fun IntRangePickerPreview() {
    val possibleValues = (-5..10).toList()
    var value by remember { mutableStateOf(possibleValues[0]) }
    ListItemPicker(
        label = { it.toString() },
        value = value,
        onValueChange = { value = it },
        list = possibleValues,
        textStyle = TextStyle(Color.White)
    )
}
