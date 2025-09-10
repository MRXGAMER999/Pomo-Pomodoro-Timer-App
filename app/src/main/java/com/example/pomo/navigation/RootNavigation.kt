package com.example.pomo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.pomo.TimerMode
import com.example.pomo.screens.PomodoroScreen
import com.example.pomo.screens.SettingsScreen
import com.example.pomo.ui.SettingsViewModel
import com.example.pomo.ui.theme.toTheme
import org.koin.androidx.compose.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
object PomodoroScreenKey : NavKey

@Serializable
data class SettingsScreenKey(val timerMode: TimerMode = TimerMode.Focus) : NavKey

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    onThemeChange: (Boolean) -> Unit
) {
    val backStack = rememberNavBackStack(PomodoroScreenKey)
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val timerSettings by settingsViewModel.timerSettings.collectAsState()


    LaunchedEffect(timerSettings.darkMode) {
        onThemeChange(timerSettings.darkMode)
    }


    val lastClickTime = remember { mutableLongStateOf(0L) }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is PomodoroScreenKey -> {
                    NavEntry(key = key) {
                        PomodoroScreen(
                            onClick = { currentMode ->
                                val currentTime = System.currentTimeMillis()
                                if (currentTime - lastClickTime.longValue > 1000) {
                                    lastClickTime.longValue = currentTime
                                    backStack.add(SettingsScreenKey(currentMode))
                                }
                            }
                        )
                    }
                }

                is SettingsScreenKey -> {
                    NavEntry(key = key) {
                        val theme = key.timerMode.toTheme(timerSettings.darkMode)
                        SettingsScreen(
                            theme = theme,
                            onClose = {
                                val currentTime = System.currentTimeMillis()
                                if (currentTime - lastClickTime.longValue > 1000) {
                                    lastClickTime.longValue = currentTime
                                    try {
                                        backStack.removeLast()
                                    } catch (e: Exception) {

                                    }
                                }
                            }
                        )
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}
