package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository

class ToggleFavoriteUseCase(
    private val repository: CourseRepository
) {
    /**
     * Переключает статус избранного.
     * @param course курс, для которого меняется статус (поле [Course.hasLike] определяет текущее состояние)
     */
    suspend operator fun invoke(course: Course) {
        if (course.hasLike) {
            repository.removeFromFavorite(course.id)
        } else {
            repository.addToFavorite(course)
        }
    }
}