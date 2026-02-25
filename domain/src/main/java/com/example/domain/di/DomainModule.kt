package com.example.domain.di

import com.example.domain.repository.CourseRepository
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.GetFavoriteCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCoursesUseCase(get<CourseRepository>()) }
    factory { GetFavoriteCoursesUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
}