package com.example.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.repository.CourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainViewModel(
    private val repository: CourseRepository
) : ViewModel() {

}