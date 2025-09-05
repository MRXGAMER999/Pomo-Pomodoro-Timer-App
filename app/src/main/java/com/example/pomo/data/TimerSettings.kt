package com.example.pomo.data

data class TimerSettings(
    val focusTimeMinutes: Int = 25,
    val shortBreakTimeMinutes: Int = 5,
    val longBreakTimeMinutes: Int = 15,
    val darkMode: Boolean = false
)

