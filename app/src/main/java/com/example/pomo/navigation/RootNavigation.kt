package com.example.pomo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.pomo.TimerMode
import com.example.pomo.screens.PomodoroDetailsScreen
import com.example.pomo.screens.PomodoroScreen
import com.example.pomo.screens.SettingsScreen
import com.example.pomo.ui.SettingsViewModel
import com.example.pomo.ui.theme.toTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object PomodoroScreenKey: NavKey


@Serializable
data class SettingsScreenKey(val timerMode: TimerMode = TimerMode.Focus): NavKey

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
){
    val backStack = rememberNavBackStack(PomodoroScreenKey)
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val timerSettings by settingsViewModel.timerSettings.collectAsState()
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when(key) {
                is PomodoroScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        PomodoroScreen(onClick = {currentMode ->

                            backStack.add(SettingsScreenKey(currentMode))})
                    }
                }
                is SettingsScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        val theme = key.timerMode.toTheme(timerSettings.darkMode)
                        SettingsScreen(
                            theme = theme,
                            onClose = {backStack.removeLast()})
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}