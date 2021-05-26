package com.example.triviaapp

import android.app.Application
import com.example.triviaapp.injection.component.AppComponent
import com.example.triviaapp.injection.component.DaggerAppComponent

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(applicationContext)
    }


}
//TODO: replace google-services.json with original