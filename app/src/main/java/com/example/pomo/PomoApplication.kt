package com.example.pomo

import android.app.Application
import com.example.pomo.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PomoApplication : Application(){
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()


            androidContext(this@PomoApplication)

            modules(appModules)
        }
    }
}