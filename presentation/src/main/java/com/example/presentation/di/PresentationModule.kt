package com.example.presentation.di

import com.example.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(
            get(),
            get()
        )
    }
}