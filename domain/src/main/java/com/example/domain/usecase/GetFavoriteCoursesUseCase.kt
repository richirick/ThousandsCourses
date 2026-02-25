package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository

class GetFavoriteCoursesUseCase(
private val repository: CourseRepository
) {
    /**
     * Загружает избранные курсы.
     * @return список [Course] у которых hasLike = true
     */
    suspend operator fun invoke(): List<Course> = repository.getFavoriteCourses()
}