package com.example.pomo.ui

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.pomo.TimerMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PomodoroViewModel: ViewModel() {
    private val _currentMode = MutableStateFlow(TimerMode.Focus)
    val currentMode: StateFlow<TimerMode> = _currentMode
    
    private val FOCUS_TIME = 25 * 60 * 1000L // 25 minutes
    private val SHORT_BREAK_TIME = 5 * 60 * 1000L // 5 minutes
    private val LONG_BREAK_TIME = 15 * 60 * 1000L // 15 minutes
    
    private val _timeLeftInMillis = MutableStateFlow(FOCUS_TIME)
    val timeLeftInMillis: StateFlow<Long> = _timeLeftInMillis
    
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning
    
    private var countDownTimer: CountDownTimer? = null
    private var currentTimeMillis = FOCUS_TIME
    
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
        
        // Reset based on current mode
        _timeLeftInMillis.value = when (_currentMode.value) {
            TimerMode.Focus -> FOCUS_TIME
            TimerMode.ShortBreak -> SHORT_BREAK_TIME
            TimerMode.LongBreak -> LONG_BREAK_TIME
        }
        currentTimeMillis = _timeLeftInMillis.value
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