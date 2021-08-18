package com.chargemap.android.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.android.compose.numberpicker.Hours
import com.chargemap.android.compose.numberpicker.HoursNumberPicker
import com.chargemap.android.compose.numberpicker.NumberPicker

@Composable
fun MainActivityUI() {

    var picker1Value by remember { mutableStateOf(0) }
    var picker2Value by remember { mutableStateOf(0) }
    var picker3Value by remember { mutableStateOf(0) }

    var hoursPicker1Value by remember { mutableStateOf(Hours(12, 43)) }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box {
                    NumberPicker(
                        modifier = Modifier
                            .align(Alignment.Center),
                        value = picker1Value,
                        range = 0..10,
                        onValueChange = {
                            picker1Value = it
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    NumberPicker(
                        modifier = Modifier
                            .weight(1f),
                        value = picker2Value,
                        range = 0..10,
                        onValueChange = {
                            picker2Value = it
                        }
                    )
                    NumberPicker(
                        modifier = Modifier
                            .weight(1f),
                        value = picker3Value,
                        range = 0..10,
                        onValueChange = {
                            picker3Value = it
                        }
                    )
                }

                HoursNumberPicker(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    value = hoursPicker1Value,
                    onValueChange = {
                        hoursPicker1Value = it
                    },
                    hoursDivider = {
                        Text(
                            modifier = Modifier.size(24.dp),
                            textAlign = TextAlign.Center,
                            text = ":"
                        )
                    }
                )
            }
        }
    }
}