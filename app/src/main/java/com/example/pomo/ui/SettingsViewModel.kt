package com.example.pomo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomo.data.SettingsRepository
import com.example.pomo.data.TimerSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    
    val timerSettings: StateFlow<TimerSettings> = settingsRepository.getTimerSettings()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TimerSettings()
        )
    
    fun updateFocusTime(minutes: Int) {
        viewModelScope.launch {
            settingsRepository.updateFocusTime(minutes)
        }
    }
    
    fun updateShortBreakTime(minutes: Int) {
        viewModelScope.launch {
            settingsRepository.updateShortBreakTime(minutes)
        }
    }
    
    fun updateLongBreakTime(minutes: Int) {
        viewModelScope.launch {
            settingsRepository.updateLongBreakTime(minutes)
        }
    }
    
    fun updateDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateDarkMode(enabled)
        }
    }


}
