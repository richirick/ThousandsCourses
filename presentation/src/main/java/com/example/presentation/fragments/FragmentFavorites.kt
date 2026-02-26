package com.example.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.FavoriteViewModel
import com.example.presentation.MainViewModel
import com.example.presentation.R
import com.example.presentation.adapter.CourseAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFavorites : Fragment(R.layout.fragment_favorites) {

//    private val viewModel: FavoriteViewModel by viewModel()
private val viewModel: MainViewModel by activityViewModel()
    private lateinit var adapter: CourseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CourseAdapter { course ->
            viewModel.toggleFavorite(course)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.coursesRecycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

//        viewModel.favorites.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
        viewModel.courses.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.filter { it.hasLike })
        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.loadFavorites()
//    }
    override fun onResume() {
        super.onResume()
        viewModel.loadCourses() // или loadFavorites если добавишь метод
    }
}