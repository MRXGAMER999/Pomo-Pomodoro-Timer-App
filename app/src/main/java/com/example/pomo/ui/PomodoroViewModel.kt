package com.example.pomo.ui

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomo.TimerMode
import com.example.pomo.data.SettingsRepository
import com.example.pomo.data.TimerSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PomodoroViewModel(private val settingsRepository: SettingsRepository): ViewModel() {
    private val _currentMode = MutableStateFlow(TimerMode.Focus)
    val currentMode: StateFlow<TimerMode> = _currentMode
    
    private var currentSettings = TimerSettings()
    
    private val _timeLeftInMillis = MutableStateFlow(25 * 60 * 1000L)
    val timeLeftInMillis: StateFlow<Long> = _timeLeftInMillis
    
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning
    
    private var countDownTimer: CountDownTimer? = null
    
    init {
        // Listen to settings changes and update timer accordingly
        settingsRepository.getTimerSettings()
            .onEach { settings ->
                currentSettings = settings
                // If timer is not running, update the displayed time
                if (!_isRunning.value) {
                    resetTimer()
                }
            }
            .launchIn(viewModelScope)
    }
    
    fun toggleTimer() {
        if (_isRunning.value) {
            pauseTimer()
        } else {
            startTimer()
        }
    }
    
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(_timeLeftInMillis.value, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeftInMillis.value = millisUntilFinished
            }
            
            override fun onFinish() {
                _timeLeftInMillis.value = 0
                _isRunning.value = false
                // Reset timer after reaching 0
                resetTimer()
            }
        }.start()
        
        _isRunning.value = true
    }
    
    private fun pauseTimer() {
        countDownTimer?.cancel()
        _isRunning.value = false
    }
    
    fun resetTimer() {
        countDownTimer?.cancel()
        _isRunning.value = false
        
        // Reset based on current mode using settings
        _timeLeftInMillis.value = when (_currentMode.value) {
            TimerMode.Focus -> currentSettings.focusTimeMinutes * 60 * 1000L
            TimerMode.ShortBreak -> currentSettings.shortBreakTimeMinutes * 60 * 1000L
            TimerMode.LongBreak -> currentSettings.longBreakTimeMinutes * 60 * 1000L
        }
    }
    
    fun skipToNextMode() {
        countDownTimer?.cancel()
        _isRunning.value = false
        
        // Cycle through modes
        _currentMode.value = when (_currentMode.value) {
            TimerMode.Focus -> TimerMode.LongBreak
            TimerMode.LongBreak -> TimerMode.ShortBreak
            TimerMode.ShortBreak -> TimerMode.Focus

        }
        
        resetTimer()
    }
    
    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}