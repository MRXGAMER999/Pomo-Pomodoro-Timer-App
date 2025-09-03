package com.example.pomo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    val darkMode by remember { mutableStateOf(false) }
    val autoResumeTimer by remember { mutableStateOf(true) }
    val sound by remember { mutableStateOf(true) }
    val notifications by remember { mutableStateOf(true) }

    var focusLength by remember { mutableStateOf(25) }
    var shortBreakLength by remember { mutableStateOf(5) }
    var longBreakLength by remember { mutableStateOf(15) }


    Surface(shape = RoundedCornerShape(20.dp),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth().padding(20.dp))

    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Settings", style = MaterialTheme.typography.headlineMedium)
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Close, contentDescription = "Close Settings")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Focus Length", style = MaterialTheme.typography.bodyLarge)
                SettingsNumberPicker(
                    initialValue = focusLength,
                    onValueChange = { newLength ->
                        focusLength = newLength}
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Short Break Length", style = MaterialTheme.typography.bodyLarge)
                SettingsNumberPicker(
                    initialValue = shortBreakLength,
                    onValueChange = { newLength ->
                        shortBreakLength = newLength}
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Long Break Length", style = MaterialTheme.typography.bodyLarge)
                SettingsNumberPicker(
                    initialValue = longBreakLength,
                    onValueChange = { newLength ->
                        longBreakLength = newLength
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Dark Mode", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = darkMode,
                    onCheckedChange = { /* Handle dark mode toggle */ },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Auto Resume Timer", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = autoResumeTimer,
                    onCheckedChange = { /* Handle auto resume timer toggle */ },
                            colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Sound", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = sound,
                    onCheckedChange = { /* Handle sound toggle */ },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Notifications", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = notifications,
                    onCheckedChange = { /* Handle notifications toggle */ },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer)
                )
            }




        }
    }
}

@Composable
fun SettingsNumberPicker(
    initialValue: Int,
    range: IntRange = 1..60,
    onValueChange: (Int) -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Number text
            Text(
                text = "$value",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                fontSize = 16.sp,
                color = Color.Black
            )

            // Vertical divider
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
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
                        tint = Color.Gray
                    )
                }

                // Horizontal divider between arrows
                Divider(
                    color = Color.LightGray,
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
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
