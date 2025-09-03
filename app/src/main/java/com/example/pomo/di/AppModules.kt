package com.example.pomo.di
import com.example.pomo.ui.PomodoroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ PomodoroViewModel() }
}

val repositoryModule = module {
    // Example: single<TimerRepository> { TimerRepositoryImpl() }
}

val useCaseModule = module {
    // Example: factory<StartTimerUseCase> { StartTimerUseCase(get()) }
}

// Combine all modules
val appModules = listOf(
    viewModelModule,
    repositoryModule,
    useCaseModule
)