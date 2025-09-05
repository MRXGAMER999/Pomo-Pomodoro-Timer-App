package com.example.pomo

import android.app.Application
import com.example.pomo.data.AppInitRepository
import com.example.pomo.data.SettingsRepository
import com.example.pomo.di.appModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PomoApplication : Application(){
    
    // Create application scope for coroutines
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin dependency injection
        startKoin {
            androidLogger()
            androidContext(this@PomoApplication)
            modules(appModules)
        }

        // Initialize database with default values
        initializeDatabase()
    }
    
    private fun initializeDatabase() {
        applicationScope.launch {
            try {
                // Get repositories from Koin after it's initialized
                val settingsRepository: SettingsRepository by inject()
                val appInitRepository: AppInitRepository by inject()
                
                // Quick initialization - DataStore handles defaults automatically
                settingsRepository.initializeDefaults()
                
                // Mark initialization as complete
                appInitRepository.markAsInitialized()
                
            } catch (e: Exception) {
                // Even on error, mark as initialized to prevent blocking UI
                // The app will work with defaults
                val appInitRepository: AppInitRepository by inject()
                appInitRepository.markAsInitialized()
                android.util.Log.w("PomoApplication", "Database initialization had issues, using defaults", e)
            }
        }
    }
}