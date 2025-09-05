package com.example.pomo.di
import com.example.pomo.data.AppInitRepository
import com.example.pomo.data.SettingsRepository
import com.example.pomo.ui.PomodoroViewModel
import com.example.pomo.ui.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<SettingsRepository> { SettingsRepository(androidContext()) }
    single<AppInitRepository> { AppInitRepository() }
}

val viewModelModule = module {
    viewModel { PomodoroViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}

val useCaseModule = module {
    // Example: factory<StartTimerUseCase> { StartTimerUseCase(get()) }
}

// Combine all modules
val appModules = listOf(
    repositoryModule,
    viewModelModule,
    useCaseModule
)