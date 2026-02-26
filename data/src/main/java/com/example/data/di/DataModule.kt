package com.example.data.di

import com.example.data.CourseRepositoryImpl
import com.example.domain.repository.CourseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<CourseRepository> { CourseRepositoryImpl(androidContext()) }
}