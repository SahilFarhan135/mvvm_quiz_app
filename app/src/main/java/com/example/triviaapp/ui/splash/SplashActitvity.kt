package com.example.triviaapp.ui.splash

import android.os.Bundle
import android.os.Handler
import com.example.triviaapp.R
import com.example.triviaapp.databinding.ActivitySplashBinding
import com.example.triviaapp.injection.component.AppComponent
import com.example.triviaapp.ui.base.BaseActivity
import com.example.triviaapp.ui.base.Navigator
import com.example.triviaapp.ui.main.MainActivity

class SplashActitvity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun layoutId(): Int =R.layout.activity_splash
    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }
    override fun addObservers() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initui()
    }

    private fun initui() {
        binding.tvSplashLabel.text="Trivia App"
        Handler().postDelayed({ navigator.startActivityClearCurrent(MainActivity::class.java,true) }, 2000)
    }



}