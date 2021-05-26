package com.example.triviaapp.ui.history

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.triviaapp.db.Quiz
import com.example.triviaapp.repository.QuizRepository
import com.example.triviaapp.ui.base.BaseViewModel
import com.example.triviaapp.util.toLiveData
import javax.inject.Inject

class HistoryViewModel@Inject constructor(private var repository: QuizRepository) : BaseViewModel() {

    private val _getQuizHistory = MutableLiveData<List<Quiz>>()
    val getQuizHistory = _getQuizHistory.toLiveData()

    fun getAllQuiz() {
        launch {
            val list = repository.getAllQuiz()
            _getQuizHistory.postValue(list)
        }
    }
}