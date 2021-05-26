package com.example.triviaapp.ui.history

import android.content.SharedPreferences
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviaapp.R
import com.example.triviaapp.databinding.ActivityHistoryBinding
import com.example.triviaapp.injection.component.AppComponent
import com.example.triviaapp.ui.base.BaseActivity
import com.example.triviaapp.ui.main.MainActivity

class HistoryActitvity : BaseActivity<ActivityHistoryBinding, HistoryViewModel>() {
    override fun layoutId(): Int = R.layout.activity_history
    override fun getViewModelClass(): Class<HistoryViewModel> = HistoryViewModel::class.java
    private val sharedPrefFile = "triviapref"


    lateinit var sharedPreferences: SharedPreferences

    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        addObservers()
    }


    private fun initUi() {
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        viewModel.getAllQuiz()
        binding.rvHistoryList.layoutManager = LinearLayoutManager(this)
        binding.rvHistoryList.adapter = HistoryAdapter()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.startActivityClearStack(MainActivity::class.java)
    }


    override fun addObservers() {
        viewModel.getQuizHistory.observe(this, {
            (binding.rvHistoryList.adapter as HistoryAdapter).submitList(it)
        })
    }


}