package com.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

//    fun loadCourses() {
//        viewModelScope.launch {
//            _courses.value = getCoursesUseCase()
//        }
//    }
fun loadCourses() {
    viewModelScope.launch {
        _courses.value = getCoursesUseCase().toList()
    }
}

    //    fun toggleFavorite(course: Course) {
//        viewModelScope.launch {
//
//            toggleFavoriteUseCase(course)
//
//            val currentList = _courses.value ?: return@launch
//
//            val updatedList = currentList.map {
//                if (it.id == course.id) {
//                    it.copy(hasLike = !course.hasLike)
//                } else {
//                    it
//                }
//            }
//
//            _courses.value = updatedList
//        }
//    }
    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            loadCourses()
        }
    }
    private var isAscending = true

    fun sortByDate() {
        val currentList = _courses.value ?: return

        val sortedList = if (isAscending) {
            currentList.sortedBy { it.publishDate }
        } else {
            currentList.sortedByDescending { it.publishDate }
        }

        isAscending = !isAscending
        _courses.value = sortedList
    }
}