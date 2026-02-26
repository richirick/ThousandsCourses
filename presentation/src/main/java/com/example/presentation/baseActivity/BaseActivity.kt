//package com.example.presentation.baseActivity
//
//import android.view.MotionEvent
//import androidx.appcompat.app.AppCompatActivity
//
//open class BaseActivity : AppCompatActivity() {
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        // Блокируем мультитач для всех наследников
//        if (event.pointerCount > 1) {
//            return true
//        }
//        return super.onTouchEvent(event)
//    }
//}