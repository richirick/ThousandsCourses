package com.example.presentation.fragments

import android.os.Bundle
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.MainViewModel
import com.example.presentation.R
import com.example.presentation.adapter.CourseAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.View
import android.widget.Button
import org.koin.androidx.viewmodel.ext.android.activityViewModel

//class MainFragment : Fragment(R.layout.fragment_main) {
//
////    private val viewModel: MainViewModel by viewModel()
//private val viewModel: MainViewModel by activityViewModel()
//    private lateinit var adapter: CourseAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        adapter = CourseAdapter { course ->
//            viewModel.toggleFavorite(course)
//        }
//
//        val recycler = view.findViewById<RecyclerView>(R.id.coursesRecycler)
//        recycler.adapter = adapter
//        recycler.layoutManager = LinearLayoutManager(requireContext())
//
//        viewModel.courses.observe(viewLifecycleOwner) { list ->
//            adapter.submitList(list)
//        }
////        viewModel.courses.observe(viewLifecycleOwner) { list ->
////            adapter.submitList(list) {
////                recycler.scrollToPosition(0)
////            }
////        }
//
//        viewModel.loadCourses()
//
//        val sortButton = view.findViewById<Button>(R.id.sortButton)
//
////        sortButton.setOnClickListener {
////            viewModel.sortByDate()
////        }
//        sortButton.setOnClickListener {
//            viewModel.sortByDate()
//            recycler.scrollToPosition(0)
//        }
//    }
//
//}
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModel()
    private lateinit var adapter: CourseAdapter
    private var shouldScrollToTop = false // флаг для прокрутки

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CourseAdapter { course ->
            viewModel.toggleFavorite(course)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.coursesRecycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.courses.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list) {
                // Этот код выполнится после того, как DiffUtil применит изменения
                if (shouldScrollToTop) {
                    recycler.scrollToPosition(0)
                    shouldScrollToTop = false
                }
            }
        }

        viewModel.loadCourses()

        val sortButton = view.findViewById<Button>(R.id.sortButton)
        sortButton.setOnClickListener {
            shouldScrollToTop = true // запоминаем, что нужно прокрутить
            viewModel.sortByDate()
        }

        recycler.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                // Если пальцев больше 1 — перехватываем событие (блокируем скролл/клик)
                return e.pointerCount > 1
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                // Пусто
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                // Пусто
            }
        })
    }
}