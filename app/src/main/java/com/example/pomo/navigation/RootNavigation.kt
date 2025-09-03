package com.example.pomo.navigation

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
import kotlinx.serialization.Serializable

@Serializable
object PomodoroScreenKey: NavKey

@Serializable
data class PomodoroDetailsScreenKey(val id: Int): NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
){
    val backStack = rememberNavBackStack(PomodoroScreenKey)
    NavDisplay(
        backStack = backStack,

        entryProvider = { key ->
            when(key) {
                is PomodoroScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        PomodoroScreen()
                    }
                }
                is PomodoroDetailsScreenKey -> {
                    NavEntry(
                        key = key,
                    ) {
                        PomodoroDetailsScreen()
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}