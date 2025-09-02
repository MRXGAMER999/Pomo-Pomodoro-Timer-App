package com.example.pomo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pomo.R
import com.example.pomo.TimerMode
import com.example.pomo.ui.PomodoroViewModel

@Composable
fun PomodoroScreen(
    modifier: Modifier = Modifier,
    viewModel: PomodoroViewModel = viewModel()
) {
    val timeLeftInMillis by viewModel.timeLeftInMillis.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val currentMode by viewModel.currentMode.collectAsState()

    // Convert milliseconds to minutes and seconds
    val minutes = (timeLeftInMillis / 1000) / 60
    val seconds = (timeLeftInMillis / 1000) % 60

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(when(currentMode){
                TimerMode.Focus -> Color(0xFFFFF2F2)
                TimerMode.LongBreak -> Color(0xFFF2FFF5)
                TimerMode.ShortBreak -> Color(0xFFF2F9FF)
            })
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {




            Spacer(modifier = Modifier.weight(1f))
            Surface(
                shape = RoundedCornerShape(50.dp),
                color = when(currentMode){
                    TimerMode.Focus -> Color(red = 255, green = 76, blue = 76, alpha = (255 * 0.15).toInt())
                    TimerMode.LongBreak -> Color(red = 77, green = 218, blue = 110, alpha = (255 * 0.15).toInt())
                    TimerMode.ShortBreak -> Color(red = 76, green = 172, blue = 255, alpha = (255 * 0.15).toInt())
                },
                border = BorderStroke(2.dp, when(currentMode){
                    TimerMode.Focus -> Color(0xFF471515)
                    TimerMode.LongBreak -> Color(0xFF14401D)
                    TimerMode.ShortBreak -> Color(0xFF153047)
                })
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
                                TimerMode.ShortBreak, TimerMode.LongBreak -> R.drawable.foucs // You can add break icons here
                            }
                        ),
                        contentDescription = "Mode icon",
                        tint = Color(0xFF471515)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (currentMode) {
                            TimerMode.Focus -> "Focus"
                            TimerMode.ShortBreak -> "Short Break"
                            TimerMode.LongBreak -> "Long Break"
                        },
                        color = Color(0xFF471515),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }




            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format("%02d", minutes),
                    fontSize = 160.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = when(currentMode){
                        TimerMode.Focus -> Color(0xFF471515)
                        TimerMode.LongBreak -> Color(0xFF14401D)
                        TimerMode.ShortBreak -> Color(0xFF153047)
                    }
                )
                Text(
                    text = String.format("%02d", seconds),
                    fontSize = 160.sp,
                    fontWeight = FontWeight.ExtraBold,

                    color = when(currentMode){
                        TimerMode.Focus -> Color(0xFF471515)
                        TimerMode.LongBreak -> Color(0xFF14401D)
                        TimerMode.ShortBreak -> Color(0xFF153047)
                    }
                )

            }



            // Control Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menu/Reset Button
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = when(currentMode){
                        TimerMode.Focus -> Color(red = 255, green = 76, blue = 76, alpha = (255 * 0.15).toInt())
                        TimerMode.LongBreak -> Color(red = 77, green = 218, blue = 110, alpha = (255 * 0.15).toInt())
                        TimerMode.ShortBreak -> Color(red = 76, green = 172, blue = 255, alpha = (255 * 0.15).toInt())
                    },
                    modifier = Modifier.size(64.dp)
                ) {
                    IconButton(
                        onClick = { viewModel.resetTimer() },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(ImageVector.vectorResource(id = R.drawable.dots),
                            contentDescription = "Reset Timer",
                            tint = when(currentMode){
                                TimerMode.Focus -> Color(0xFF471515)
                                TimerMode.LongBreak -> Color(0xFF14401D)
                                TimerMode.ShortBreak -> Color(0xFF153047)
                            })
                    }
                }

                // Pause/Play Button (Main button)
                Surface(
                    shape = RoundedCornerShape(25.dp),
                    color = when(currentMode){
                        TimerMode.Focus -> Color(red = 255, green = 76, blue = 76, alpha = (255 * 0.71).toInt())
                        TimerMode.LongBreak -> Color(red = 77, green = 218, blue = 110, alpha = (255 * 0.71).toInt())
                        TimerMode.ShortBreak -> Color(red = 76, green = 172, blue = 255, alpha = (255 * 0.71).toInt())
                    },
                    modifier = Modifier.size(100.dp, 70.dp)
                ) {
                    IconButton(
                        onClick = { viewModel.toggleTimer() },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = if (isRunning) {
                                Icons.Default.Pause
                            } else {
                                Icons.Default.PlayArrow
                            },
                            contentDescription = if (isRunning) "Pause" else "Play",
                            tint = when(currentMode){
                                TimerMode.Focus -> Color(0xFF471515)
                                TimerMode.LongBreak -> Color(0xFF14401D)
                                TimerMode.ShortBreak -> Color(0xFF153047)
                            },
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                // Skip Button
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = when(currentMode){
                        TimerMode.Focus -> Color(red = 255, green = 76, blue = 76, alpha = (255 * 0.15).toInt())
                        TimerMode.LongBreak -> Color(red = 77, green = 218, blue = 110, alpha = (255 * 0.15).toInt())
                        TimerMode.ShortBreak -> Color(red = 76, green = 172, blue = 255, alpha = (255 * 0.15).toInt())
                    },
                    modifier = Modifier.size(64.dp)
                ) {
                    IconButton(
                        onClick = { viewModel.skipToNextMode() },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.FastForward,
                            contentDescription = "Skip",
                            tint = when(currentMode){
                                TimerMode.Focus -> Color(0xFF471515)
                                TimerMode.LongBreak -> Color(0xFF14401D)
                                TimerMode.ShortBreak -> Color(0xFF153047)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PomodoroScreenPreview() {
    PomodoroScreen()
}