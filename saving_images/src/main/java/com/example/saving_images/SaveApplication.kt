package com.example.saving_images

import android.app.Application
import android.util.DisplayMetrics

class SaveApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
    }
}