# Jetpack Compose Number Picker

Android library providing a Number Picker for Jetpack Compose.

[![chargemap](https://github.com/chargemap.png?size=50)](https://chargemap.com)

[![Maven version](https://img.shields.io/maven-central/v/com.chargemap.compose/numberpicker?style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/com.chargemap.compose/numberpicker)

![License MIT](https://img.shields.io/badge/MIT-9E9F9F?style=flat-square&label=License)
![Android minimuml version](https://img.shields.io/badge/21+-9E9F9F?style=flat-square&label=Minimum&logo=android)

## Showcase

<img src="art/showcase.gif" width="250"/>

## Installation

In your **module** *build.gradle* :

```
dependencies {
  implementation "com.chargemap.compose:numberpicker:latestVersion"
}
```

## Usage

### Simple NumberPicker

```
var pickerValue by remember { mutableStateOf(0) }

NumberPicker(
    value = pickerValue,
    range = 0..10,
    onValueChange = {
        pickerValue = it
    }
)

```

### 24 hours HoursNumberPicker

```
var pickerValue by remember { mutableStateOf<Hours>(FullHours(12, 43)) }

HoursNumberPicker(
    dividersColor = MaterialTheme.colors.primary,
    value = pickerValue,
    onValueChange = {
        pickerValue = it
    },
    hoursDivider = {
        Text(
            modifier = Modifier.size(24.dp),
            textAlign = TextAlign.Center,
            text = ":"
        )
    }
)

```

### AM/PM HoursNumberPicker

```
var pickerValue by remember { mutableStateOf<Hours>(AMPMHours(9, 12, AMPMHours.DayTime.PM )) }

HoursNumberPicker(
    dividersColor = MaterialTheme.colors.primary,
    value = pickerValue,
    onValueChange = {
        pickerValue = it
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

```

## Contributors

| [![raphaël](https://github.com/r4phab.png?size=120)](https://github.com/r4phab) | [Raphaël Bertin](https://github.com/r4phab) |
|:------------------------------------------------------------------------------:|--------------|
