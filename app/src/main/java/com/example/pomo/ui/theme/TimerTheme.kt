package com.example.pomo.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.pomo.TimerMode

sealed class TimerTheme(
    val background: Color,
    val primary: Color,
    val surfaceLight: Color,
    val surfaceMedium: Color,
    val border: Color
) {
    object Focus : TimerTheme(
        background = Color(0xFFFFF2F2),
        primary = Color(0xFF471515),
        surfaceLight = Color(0x26FF4C4C), // 15% opacity
        surfaceMedium = Color(0xB5FF4C4C), // 71% opacity
        border = Color(0xFF471515)
    )

    object LongBreak : TimerTheme(
        background = Color(0xFFF2FFF5),
        primary = Color(0xFF14401D),
        surfaceLight = Color(0x264DDA6E), // 15% opacity
        surfaceMedium = Color(0xB54DDA6E), // 71% opacity
        border = Color(0xFF14401D)
    )

    object ShortBreak : TimerTheme(
        background = Color(0xFFF2F9FF),
        primary = Color(0xFF153047),
        surfaceLight = Color(0x264CACFF), // 15% opacity
        surfaceMedium = Color(0xB54CACFF), // 71% opacity
        border = Color(0xFF153047)
    )
}

// Extension function to convert TimerMode to TimerTheme
fun TimerMode.toTheme(): TimerTheme = when (this) {
    TimerMode.Focus -> TimerTheme.Focus
    TimerMode.ShortBreak -> TimerTheme.ShortBreak
    TimerMode.LongBreak -> TimerTheme.LongBreak
}