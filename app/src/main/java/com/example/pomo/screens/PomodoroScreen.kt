package com.example.pomo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomo.R
import com.example.pomo.TimerMode
import com.example.pomo.ui.PomodoroViewModel
import com.example.pomo.ui.theme.toTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PomodoroScreen(
    modifier: Modifier = Modifier,
    viewModel: PomodoroViewModel = koinViewModel(),
    onClick: (TimerMode) -> Unit = {}
) {
    val timeLeftInMillis by viewModel.timeLeftInMillis.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val currentMode by viewModel.currentMode.collectAsState()

    // Get theme colors for current mode
    val theme = currentMode.toTheme()

    // Convert milliseconds to minutes and seconds
    val minutes = (timeLeftInMillis / 1000) / 60
    val seconds = (timeLeftInMillis / 1000) % 60

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = theme.background
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            // Mode indicator surface
            Surface(
                shape = RoundedCornerShape(50.dp),
                color = theme.surfaceLight,
                border = BorderStroke(2.dp, theme.border)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(
                            id = when (currentMode) {
                                TimerMode.Focus -> R.drawable.foucs
                                TimerMode.LongBreak -> R.drawable.coffee_icon
                                TimerMode.ShortBreak -> R.drawable.coffee_icon
                            }
                        ),
                        contentDescription = "Mode icon",
                        tint = theme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (currentMode) {
                            TimerMode.Focus -> "Focus"
                            TimerMode.ShortBreak -> "Short Break"
                            TimerMode.LongBreak -> "Long Break"
                        },
                        color = theme.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Timer display
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format("%02d", minutes),
                    fontSize = 160.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = theme.primary
                )
                Text(
                    text = String.format("%02d", seconds),
                    fontSize = 160.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = theme.primary
                )
            }

            // Control Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Reset Button
                TimerControlButton(
                    onClick = { onClick(currentMode) },
                    backgroundColor = theme.surfaceLight,
                    contentColor = theme.primary,
                    size = 64.dp
                ) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.dots),
                        contentDescription = "Reset Timer"
                    )
                }

                // Play/Pause Button (Main button)
                TimerControlButton(
                    onClick = { viewModel.toggleTimer() },
                    backgroundColor = theme.surfaceMedium,
                    contentColor = theme.primary,
                    size = 100.dp,
                    height = 70.dp
                ) {
                    Icon(
                        imageVector = if (isRunning) {
                            Icons.Default.Pause
                        } else {
                            Icons.Default.PlayArrow
                        },
                        contentDescription = if (isRunning) "Pause" else "Play",
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Skip Button
                TimerControlButton(
                    onClick = { viewModel.skipToNextMode() },
                    backgroundColor = theme.surfaceLight,
                    contentColor = theme.primary,
                    size = 64.dp
                ) {
                    Icon(
                        imageVector = Icons.Default.FastForward,
                        contentDescription = "Skip"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun TimerControlButton(
    onClick: () -> Unit,
    backgroundColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color,
    size: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp = size,
    content: @Composable () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(if (size == height) 20.dp else 25.dp),
        color = backgroundColor,
        modifier = Modifier.size(size, height)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            androidx.compose.runtime.CompositionLocalProvider(
                androidx.compose.material3.LocalContentColor provides contentColor
            ) {
                content()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PomodoroScreenPreview() {
    PomodoroScreen(onClick = {

    })
}