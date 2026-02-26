package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Course
import com.example.presentation.R
import com.example.presentation.databinding.CourseItemBinding


class CourseAdapter(
    private val onBookmarkClick: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    inner class CourseViewHolder(
        private val binding: CourseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) = with(binding) {
            title.text = course.title
            description.text = course.text
            price.text = course.price
            rating.text = course.rate.toString()
            startDate.text = course.startDate

            if (course.hasLike) {
                bookmarkIcon.setColorFilter(root.context.getColor(R.color.green))
            } else {
                bookmarkIcon.setColorFilter(root.context.getColor(R.color.white1))
            }

            bookmarkIcon.setOnClickListener {
                onBookmarkClick(course)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}