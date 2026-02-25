package com.example.data

import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository

class CourseRepositoryImpl : CourseRepository {
    override suspend fun getCourses(): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteCourses(): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorite(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorite(courseId: Int) {
        TODO("Not yet implemented")
    }
}