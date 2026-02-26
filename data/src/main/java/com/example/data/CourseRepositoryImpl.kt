package com.example.data

import android.content.Context
import com.example.domain.model.Course
import com.example.domain.repository.CourseRepository
import com.google.gson.Gson

class CourseRepositoryImpl(
    private val context: Context
) : CourseRepository {

    private val courses = mutableListOf<Course>()

    init {
        loadFromAssets()
    }

    private fun loadFromAssets() {
        val json = context.assets
            .open("courses.json")
            .bufferedReader()
            .use { it.readText() }

        val list = Gson().fromJson(json, Array<Course>::class.java).toList()
        courses.addAll(list)
    }

    override suspend fun getCourses(): List<Course> {
        return courses
    }

    override suspend fun getFavoriteCourses(): List<Course> {
        return courses.filter { it.hasLike }
    }

    override suspend fun addToFavorite(course: Course) {
        courses.replaceAll {
            if (it.id == course.id) it.copy(hasLike = true) else it
        }
    }

    override suspend fun removeFromFavorite(courseId: Int) {
        courses.replaceAll {
            if (it.id == courseId) it.copy(hasLike = false) else it
        }
    }
}