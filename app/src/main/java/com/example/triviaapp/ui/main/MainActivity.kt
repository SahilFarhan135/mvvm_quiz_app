package com.example.triviaapp.ui.main

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.triviaapp.R
import com.example.triviaapp.databinding.ActivityMainBinding
import com.example.triviaapp.injection.component.AppComponent
import com.example.triviaapp.ui.base.BaseActivity
import com.example.triviaapp.ui.quizactivity.QuizActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun layoutId(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }


    lateinit var sharedPreferences: SharedPreferences
    private val sharedPrefFile = "triviapref"
    private var userName=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        clickListner()
        addObservers()
    }

    private fun initUi() {
         sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

    }
    private fun clickListner() {
        binding.btPlay.setOnClickListener {
            viewModel.CheckInput(binding.etUsername.text.toString().trim())
            uiUtil.hideSoftKeyboard(this)
        }
    }

    override fun addObservers() {
        viewModel.valideInput.observe(this, {
            if (it.equals("1")) {
                userName = binding.etUsername.text.toString().trim()
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("username", userName)
                editor.apply()
                navigator.startActivity(QuizActivity::class.java, true)
            } else {
                uiUtil.showToast("Please Provide Your Name to Play Quiz")
            }
        })
    }




}