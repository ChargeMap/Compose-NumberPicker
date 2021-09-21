package com.chargemap.compose.numberpicker

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

fun getIndexForOffset(range: Iterable<Int>, value: Int, offset: Float, halfNumbersColumnHeightPx: Float): Int {
    val indexOf = range.indexOf(value) + range.first() - (offset / halfNumbersColumnHeightPx).toInt()
    return when (val indexInRange = range.indexOf(indexOf)) {
        -1 -> indexOf
        else -> indexInRange
    }.let {
        minOf(it, range.count() - 1)
    }.let {
        maxOf(0, it)
    }
}


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
) {
    val minimumAlpha = 0.3f
    val verticalMargin = 8.dp
    val numbersColumnHeight = 80.dp
    val halfNumbersColumnHeight = numbersColumnHeight / 2
    val halfNumbersColumnHeightPx = with(LocalDensity.current) { halfNumbersColumnHeight.toPx() }

    val coroutineScope = rememberCoroutineScope()

    val animatedOffset = remember { Animatable(0f) }
        .apply {
            val offsetRange = remember(value, range) {
                -((range.count() - 1) - range.indexOf(value)) * halfNumbersColumnHeightPx to value * halfNumbersColumnHeightPx
            }
            updateBounds(offsetRange.first, offsetRange.second)
        }

    val coercedAnimatedOffset = animatedOffset.value % halfNumbersColumnHeightPx

    val indexOfElement = getIndexForOffset(range, value, animatedOffset.value, halfNumbersColumnHeightPx)

    var dividersWidth by remember { mutableStateOf(0.dp) }

    Layout(
        modifier = modifier
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { deltaY ->
                    coroutineScope.launch {
                        animatedOffset.snapTo(animatedOffset.value + deltaY)
                    }
                },
                onDragStopped = { velocity ->
                    coroutineScope.launch {
                        val endValue = animatedOffset.fling(
                            initialVelocity = velocity,
                            animationSpec = exponentialDecay(frictionMultiplier = 20f),
                            adjustTarget = { target ->
                                val coercedTarget = target % halfNumbersColumnHeightPx
                                val coercedAnchors = listOf(-halfNumbersColumnHeightPx, 0f, halfNumbersColumnHeightPx)
                                val coercedPoint = coercedAnchors.minByOrNull { kotlin.math.abs(it - coercedTarget) }!!
                                val base = halfNumbersColumnHeightPx * (target / halfNumbersColumnHeightPx).toInt()
                                coercedPoint + base
                            }
                        ).endState.value

                        val result = range.elementAt(getIndexForOffset(range, value, endValue, halfNumbersColumnHeightPx))
                        onValueChange(result)
                        animatedOffset.snapTo(0f)
                    }
                }
            )
            .padding(vertical = numbersColumnHeight / 3 + verticalMargin * 2),
        content = {
            Box(
                modifier
                    .width(dividersWidth)
                    .height(2.dp)
                    .background(color = dividersColor)
            )
            Box(
                modifier = Modifier
                    .padding(vertical = verticalMargin, horizontal = 20.dp)
                    .offset { IntOffset(x = 0, y = coercedAnimatedOffset.roundToInt()) }
            ) {
                val baseLabelModifier = Modifier.align(Alignment.Center)
                ProvideTextStyle(textStyle) {
                    if (indexOfElement > 0)
                        Label(
                            text = label(range.elementAt(indexOfElement - 1)),
                            modifier = baseLabelModifier
                                .offset(y = -halfNumbersColumnHeight)
                                .alpha(maxOf(minimumAlpha, coercedAnimatedOffset / halfNumbersColumnHeightPx))
                        )
                    Label(
                        text = label(range.elementAt(indexOfElement)),
                        modifier = baseLabelModifier
                            .alpha((maxOf(minimumAlpha, 1 - kotlin.math.abs(coercedAnimatedOffset) / halfNumbersColumnHeightPx)))
                    )
                    if (indexOfElement < range.count() - 1)
                        Label(
                            text = label(range.elementAt(indexOfElement + 1)),
                            modifier = baseLabelModifier
                                .offset(y = halfNumbersColumnHeight)
                                .alpha(maxOf(minimumAlpha, -coercedAnimatedOffset / halfNumbersColumnHeightPx))
                        )
                }
            }
            Box(
                modifier
                    .width(dividersWidth)
                    .height(2.dp)
                    .background(color = dividersColor)
            )
        }
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each children
            measurable.measure(constraints)
        }

        dividersWidth = placeables
            .drop(1)
            .first()
            .width
            .toDp()

        // Set the size of the layout as big as it can
        layout(dividersWidth.toPx().toInt(), placeables
            .sumOf {
                it.height
            }
        ) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->

                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Composable
private fun Label(text: String, modifier: Modifier) {
    Text(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = {
                // FIXME: Empty to disable text selection
            })
        },
        text = text,
        textAlign = TextAlign.Center,
    )
}

private suspend fun Animatable<Float, AnimationVector1D>.fling(
    initialVelocity: Float,
    animationSpec: DecayAnimationSpec<Float>,
    adjustTarget: ((Float) -> Float)?,
    block: (Animatable<Float, AnimationVector1D>.() -> Unit)? = null,
): AnimationResult<Float, AnimationVector1D> {
    val targetValue = animationSpec.calculateTargetValue(value, initialVelocity)
    val adjustedTarget = adjustTarget?.invoke(targetValue)
    return if (adjustedTarget != null) {
        animateTo(
            targetValue = adjustedTarget,
            initialVelocity = initialVelocity,
            block = block
        )
    } else {
        animateDecay(
            initialVelocity = initialVelocity,
            animationSpec = animationSpec,
            block = block,
        )
    }
}