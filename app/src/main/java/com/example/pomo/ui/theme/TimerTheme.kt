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




    object FocusDark : TimerTheme(
        background = Color(0xFF0D0404),
        primary = Color(0xFFFFF2F2),
        surfaceLight = Color(0x26FF4C4C),
        surfaceMedium = Color(0xB5FF4C4C),
        border = Color(0xFFFFF2F2)
    )

    object LongBreakDark : TimerTheme(
        background = Color(0xFF040D06),
        primary = Color(0xFFF2FFF5),
        surfaceLight = Color(0x264DDA6E),
        surfaceMedium = Color(0x9E4DDA6E),
        border = Color(0xFFF2FFF5)
    )

    object ShortBreakDark : TimerTheme(
        background = Color(0xFF04090D),
        primary = Color(0xFFF2F9FF),
        surfaceLight = Color(0x264CACFF),
        surfaceMedium = Color(0x9E4CACFF),
        border = Color(0xFFF2F9FF)
    )

}




// Extension function to convert TimerMode to TimerTheme
fun TimerMode.toTheme(): TimerTheme = when (this) {
    TimerMode.Focus -> TimerTheme.Focus
    TimerMode.ShortBreak -> TimerTheme.ShortBreak
    TimerMode.LongBreak -> TimerTheme.LongBreak
}

// Extension function to convert TimerMode to TimerTheme with dark mode support
fun TimerMode.toTheme(isDarkMode: Boolean): TimerTheme = when (this) {
    TimerMode.Focus -> if (isDarkMode) TimerTheme.FocusDark else TimerTheme.Focus
    TimerMode.ShortBreak -> if (isDarkMode) TimerTheme.ShortBreakDark else TimerTheme.ShortBreak
    TimerMode.LongBreak -> if (isDarkMode) TimerTheme.LongBreakDark else TimerTheme.LongBreak
}