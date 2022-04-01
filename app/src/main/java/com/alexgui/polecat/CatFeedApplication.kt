package com.alexgui.polecat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatFeedApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}