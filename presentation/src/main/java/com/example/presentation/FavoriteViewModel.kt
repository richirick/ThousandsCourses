package com.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.GetFavoriteCoursesUseCase
import com.example.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _favorites = MutableLiveData<List<Course>>()
    val favorites: LiveData<List<Course>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoriteCoursesUseCase()
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            loadFavorites()
        }
    }
}