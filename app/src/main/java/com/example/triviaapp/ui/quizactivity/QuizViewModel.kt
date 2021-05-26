package com.example.triviaapp.ui.quizactivity

import androidx.lifecycle.MutableLiveData
import com.example.triviaapp.db.Quiz
import com.example.triviaapp.repository.QuizRepository
import com.example.triviaapp.ui.base.BaseViewModel
import com.example.triviaapp.util.toLiveData
import javax.inject.Inject

class QuizViewModel @Inject constructor(private var repository: QuizRepository) : BaseViewModel() {
    private val _addquiz = MutableLiveData<String>()
    val addquiz = _addquiz.toLiveData()

    fun addQuiz(quiz: Quiz) {
        launch {
            repository.addQuiz(quiz)
            _addquiz.postValue("1")
        }
    }


}


