package com.example.pomo.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppInitRepository {
    
    private val _isInitialized = MutableStateFlow(false)
    val isInitialized: StateFlow<Boolean> = _isInitialized
    
    private val _initializationError = MutableStateFlow<String?>(null)
    val initializationError: StateFlow<String?> = _initializationError
    
    fun markAsInitialized() {
        _isInitialized.value = true
        _initializationError.value = null
    }
    
    fun markInitializationFailed(error: String) {
        _isInitialized.value = false
        _initializationError.value = error
    }
    
    fun clearError() {
        _initializationError.value = null
    }
}
