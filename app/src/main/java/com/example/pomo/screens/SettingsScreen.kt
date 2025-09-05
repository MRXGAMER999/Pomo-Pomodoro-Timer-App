package com.example.pomo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomo.TimerMode
import com.example.pomo.ui.SettingsViewModel
import com.example.pomo.ui.theme.TimerTheme
import com.example.pomo.ui.theme.toTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    theme: TimerTheme = TimerMode.Focus.toTheme(false), // Default theme (light mode)
    onClose: () -> Unit = {},
    viewModel: SettingsViewModel = koinViewModel()
) {
    val timerSettings by viewModel.timerSettings.collectAsState()

    SettingsScreenContent(
        theme = theme,
        focusLength = timerSettings.focusTimeMinutes,
        shortBreakLength = timerSettings.shortBreakTimeMinutes,
        longBreakLength = timerSettings.longBreakTimeMinutes,
        darkMode = timerSettings.darkMode,
        onClose = onClose,
        onUpdateFocus = { viewModel.updateFocusTime(it) },
        onUpdateShortBreak = { viewModel.updateShortBreakTime(it) },
        onUpdateLongBreak = { viewModel.updateLongBreakTime(it) },
        onUpdateDarkMode = { viewModel.updateDarkMode(it) }
    )
}

@Composable
fun SettingsScreenContent(
    theme: TimerTheme,
    focusLength: Int,
    shortBreakLength: Int,
    longBreakLength: Int,
    darkMode: Boolean,
    onClose: () -> Unit,
    onUpdateFocus: (Int) -> Unit,
    onUpdateShortBreak: (Int) -> Unit,
    onUpdateLongBreak: (Int) -> Unit,
    onUpdateDarkMode: (Boolean) -> Unit
) {
    // Local toggles – preview-safe and independent of DI (except dark mode)
    val autoResumeTimer by remember { mutableStateOf(true) }
    val sound by remember { mutableStateOf(true) }
    val notifications by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = theme.background,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = theme.surfaceLight,
                border = BorderStroke(1.dp, theme.border),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Header with close button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Settings",
                            style = MaterialTheme.typography.headlineMedium,
                            color = theme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        ThemedIconButton(
                            onClick = onClose,
                            theme = theme
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close Settings"
                            )
                        }
                    }

                    // Timer Length Settings
                    SettingsRow(
                        label = "Focus Length",
                        theme = theme
                    ) {
                        ThemedNumberPicker(
                            initialValue = focusLength,
                            onValueChange = { newValue ->
                                onUpdateFocus(newValue)
                            },
                            theme = theme
                        )
                    }

                    SettingsRow(
                        label = "Short Break Length",
                        theme = theme
                    ) {
                        ThemedNumberPicker(
                            initialValue = shortBreakLength,
                            onValueChange = { newValue ->
                                onUpdateShortBreak(newValue)
                            },
                            theme = theme
                        )
                    }

                    SettingsRow(
                        label = "Long Break Length",
                        theme = theme
                    ) {
                        ThemedNumberPicker(
                            initialValue = longBreakLength,
                            onValueChange = { newValue ->
                                onUpdateLongBreak(newValue)
                            },
                            theme = theme
                        )
                    }

                    // Toggle Settings
                                         SettingsRow(
                         label = "Dark Mode",
                         theme = theme
                     ) {
                         ThemedSwitch(
                             checked = darkMode,
                             onCheckedChange = onUpdateDarkMode,
                             theme = theme
                         )
                     }

                    SettingsRow(
                        label = "Auto Resume Timer",
                        theme = theme
                    ) {
                        ThemedSwitch(
                            checked = autoResumeTimer,
                            onCheckedChange = { /* Handle auto resume timer toggle */ },
                            theme = theme
                        )
                    }

                    SettingsRow(
                        label = "Sound",
                        theme = theme
                    ) {
                        ThemedSwitch(
                            checked = sound,
                            onCheckedChange = { /* Handle sound toggle */ },
                            theme = theme
                        )
                    }

                    SettingsRow(
                        label = "Notifications",
                        theme = theme
                    ) {
                        ThemedSwitch(
                            checked = notifications,
                            onCheckedChange = { /* Handle notifications toggle */ },
                            theme = theme
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsRow(
    label: String,
    theme: TimerTheme,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = theme.primary,
            fontWeight = FontWeight.Medium
        )
        content()
    }
}

@Composable
private fun ThemedIconButton(
    onClick: () -> Unit,
    theme: TimerTheme,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = theme.surfaceMedium,
        modifier = Modifier.size(40.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            androidx.compose.runtime.CompositionLocalProvider(
                androidx.compose.material3.LocalContentColor provides theme.primary
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ThemedSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    theme: TimerTheme
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = theme.primary,
            checkedTrackColor = theme.surfaceMedium,
            uncheckedThumbColor = theme.border.copy(alpha = 0.6f),
            uncheckedTrackColor = theme.surfaceLight,
            checkedBorderColor = theme.border,
            uncheckedBorderColor = theme.border.copy(alpha = 0.4f)
        )
    )
}

@Composable
private fun ThemedNumberPicker(
    initialValue: Int,
    range: IntRange = 1..60,
    onValueChange: (Int) -> Unit,
    theme: TimerTheme
) {
    var value by remember(initialValue) { mutableStateOf(initialValue) }

    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White.copy(alpha = 0.3f),
        border = BorderStroke(1.dp, theme.border)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Number text
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$value",
                    fontSize = 16.sp,
                    color = theme.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            // Vertical divider
            VerticalDivider(
                color = theme.border.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier.fillMaxHeight()
            )

            // Arrow buttons column
            Column(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                // Up arrow
                IconButton(
                    onClick = {
                        val newValue = (value + 1).coerceIn(range)
                        if (newValue != value) {
                            value = newValue
                            onValueChange(value)
                        }
                    },
                    modifier = Modifier
                        .size(20.dp)
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Increase value",
                        modifier = Modifier.size(14.dp),
                        tint = theme.primary
                    )
                }

                // Horizontal divider between arrows
                HorizontalDivider(
                    color = theme.border.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                // Down arrow
                IconButton(
                    onClick = {
                        val newValue = (value - 1).coerceIn(range)
                        if (newValue != value) {
                            value = newValue
                            onValueChange(value)
                        }
                    },
                    modifier = Modifier
                        .size(20.dp)
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Decrease value",
                        modifier = Modifier.size(14.dp),
                        tint = theme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreenContent(
        theme = TimerMode.LongBreak.toTheme(true),
        focusLength = 25,
        shortBreakLength = 5,
        longBreakLength = 15,
        darkMode = false,
        onClose = {},
        onUpdateFocus = {},
        onUpdateShortBreak = {},
        onUpdateLongBreak = {},
        onUpdateDarkMode = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenFocusPreview() {
    SettingsScreenContent(
        theme = TimerMode.Focus.toTheme(false),
        focusLength = 25,
        shortBreakLength = 5,
        longBreakLength = 15,
        darkMode = false,
        onClose = {},
        onUpdateFocus = {},
        onUpdateShortBreak = {},
        onUpdateLongBreak = {},
        onUpdateDarkMode = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenBreakPreview() {
    SettingsScreenContent(
        theme = TimerMode.ShortBreak.toTheme(true),
        focusLength = 25,
        shortBreakLength = 5,
        longBreakLength = 15,
        darkMode = true,
        onClose = {},
        onUpdateFocus = {},
        onUpdateShortBreak = {},
        onUpdateLongBreak = {},
        onUpdateDarkMode = {}
    )
}