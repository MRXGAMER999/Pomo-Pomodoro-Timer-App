package com.example.pomo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.pomo.screens.PomodoroDetailsScreen
import com.example.pomo.screens.PomodoroScreen
import com.example.pomo.screens.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object PomodoroScreenKey: NavKey

@Serializable
object SettingsScreenKey: NavKey

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
){
    val backStack = rememberNavBackStack(PomodoroScreenKey)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = { key ->
            when(key) {
                is PomodoroScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        PomodoroScreen(onClick = {backStack.add(SettingsScreenKey)})
                    }
                }
                is SettingsScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        SettingsScreen(onClose = {backStack.removeLast()})
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}