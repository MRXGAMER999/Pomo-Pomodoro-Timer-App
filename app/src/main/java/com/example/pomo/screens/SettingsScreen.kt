package com.example.pomo.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    range: IntRange = 1..60, // Default range from 1 to 60
    onValueChange: (Int) -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // The number picker box
        Surface(modifier = Modifier.width(80.dp) ,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Text displaying the current value
                Text(
                    text = "$value",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 18.sp
                )
                // Vertical separator line
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(50.dp)  // Set the height of the divider
                        .width(1.dp)
                )// Set the thickness (width) of the divider

                // Up and Down buttons
                Column {
                    // Up Arrow
                    IconButton(
                        onClick = {
                            // Increment value, ensuring it doesn't go above the max
                            val newValue = (value + 1).coerceIn(range)
                            if (newValue != value) {
                                value = newValue
                                onValueChange(value)
                            }
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Increase value"
                        )
                    }
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth())
                    // Down Arrow
                    IconButton(
                        onClick = {
                            // Decrement value, ensuring it doesn't go below the min
                            val newValue = (value - 1).coerceIn(range)
                            if (newValue != value) {
                                value = newValue
                                onValueChange(value)
                            }
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Decrease value"
                        )
                    }
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
