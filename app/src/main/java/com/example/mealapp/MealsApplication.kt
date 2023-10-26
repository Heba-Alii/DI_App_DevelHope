package com.example.mealapp

import android.app.Application
import android.os.StrictMode
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class MealsApplication : Application()
