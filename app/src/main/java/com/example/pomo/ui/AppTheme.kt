package com.example.pomo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.pomo.ui.theme.PomoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppTheme(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    content: @Composable () -> Unit
) {
    val timerSettings by settingsViewModel.timerSettings.collectAsState()
    
    PomoTheme(
        darkTheme = timerSettings.darkMode,
        content = content
    )
}
