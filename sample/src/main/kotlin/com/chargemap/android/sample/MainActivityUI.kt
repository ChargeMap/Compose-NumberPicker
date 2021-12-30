package com.chargemap.android.sample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.*

@Composable
fun MainActivityUI() {

    var picker1Value by remember { mutableStateOf(0) }

    var hoursPicker1Value by remember { mutableStateOf<Hours>(FullHours(12, 43)) }
    var hoursPicker2Value by remember { mutableStateOf<Hours>(AMPMHours(9, 43, AMPMHours.DayTime.PM)) }
    var hoursPicker3Value by remember { mutableStateOf<Hours>(FullHours(9, 20)) }
    var hoursPicker4Value by remember { mutableStateOf<Hours>(FullHours(11, 36)) }

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
                    NumberPicker(
                        value = picker1Value,
                        range = 0..10,
                        onValueChange = {
                            picker1Value = it
                        }
                    )

                    HoursNumberPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        dividersColor = MaterialTheme.colors.error,
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

                    HoursNumberPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        dividersColor = MaterialTheme.colors.secondary,
                        value = hoursPicker2Value,
                        onValueChange = {
                            hoursPicker2Value = it
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

                    HoursNumberPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        value = hoursPicker3Value,
                        onValueChange = {
                            hoursPicker3Value = it
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

                    HoursNumberPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        value = hoursPicker4Value,
                        onValueChange = {
                            hoursPicker4Value = it
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
            }
        }
    }
}