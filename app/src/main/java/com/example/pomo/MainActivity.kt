package com.example.pomo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pomo.data.AppInitRepository
import com.example.pomo.navigation.NavigationRoot
import com.example.pomo.ui.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)


        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = 0x00000000,
                darkScrim = 0x00000000
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = 0x00000000,
                darkScrim = 0x00000000
            )
        )

        splashScreen.setKeepOnScreenCondition {
            val appInitRepository: AppInitRepository by inject()
            !appInitRepository.isInitialized.value
        }

        setContent {
            AppTheme {
                MainContent()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
private fun MainActivity.MainContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        )

        NavigationRoot(
            modifier = Modifier.fillMaxSize(),
            onThemeChange = { darkMode ->
                updateSystemBars(darkMode)
            }
        )
    }
}


private fun MainActivity.updateSystemBars(darkMode: Boolean) {
    val transparent = 0x00000000 // fully transparent

    val statusBarStyle = if (darkMode) {
        SystemBarStyle.dark(transparent)
    } else {
        SystemBarStyle.light(transparent, transparent)
    }

    val navBarStyle = if (darkMode) {
        SystemBarStyle.dark(transparent)
    } else {
        SystemBarStyle.light(transparent, transparent)
    }

    enableEdgeToEdge(
        statusBarStyle = statusBarStyle,
        navigationBarStyle = navBarStyle
    )
}
