> [!WARNING]  
> This repository is archived, it is now avalaible at https://github.com/charge-map/Compose-NumberPicker

# Jetpack Compose Number Picker

Android library providing a Number Picker for Jetpack Compose.

[![chargemap](https://github.com/chargemap.png?size=50)](https://chargemap.com)

[![Maven version](https://img.shields.io/maven-central/v/com.chargemap.compose/numberpicker?style=for-the-badge)](https://mvnrepository.com/artifact/com.chargemap.compose/numberpicker)

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

<img src="art/sample_number.png" width="80"/>

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

<img src="art/sample_24hours.png" width="250"/>

```
var pickerValue by remember { mutableStateOf<Hours>(FullHours(12, 43)) }

HoursNumberPicker(
    dividersColor = MaterialTheme.colors.primary,
    leadingZero = false,
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

<img src="art/sample_ampm.png" width="250"/>

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

### List Picker

<img src="art/sample_list.png" width="80"/>

```
val possibleValues = listOf("🍎", "🍊", "🍉", "🥭", "🍈", "🍇", "🍍")
var state by remember { mutableStateOf(possibleValues[0]) }
ListItemPicker(
    label = { it },
    value = state,
    onValueChange = { state = it },
    list = possibleValues
)

```

## Contributors

| [![chargemap](https://github.com/chargemap.png?size=50)](https://github.com/chargemap) | [Chargemap](https://github.com/chargemap) | Author |
|--------------|--------------|--------------|
| [![pandasys](https://github.com/pandasys.png?size=50)](https://github.com/pandasys) | [Eric A. Snell](https://github.com/pandasys) | [Pull Request](https://github.com/ChargeMap/Compose-NumberPicker/pull/2) |
| [![pandasys](https://github.com/cjrcodes.png?size=50)](https://github.com/pandasys) | [Christian R](https://github.com/cjrcodes) | [Pull Request](https://github.com/ChargeMap/Compose-NumberPicker/pull/8) |
