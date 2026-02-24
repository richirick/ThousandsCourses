package com.example.domain.repository

import com.example.domain.model.Course

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun getFavoriteCourses(): List<Course>
    suspend fun addToFavorite(course: Course)
    suspend fun removeFromFavorite(courseId: Int)
}