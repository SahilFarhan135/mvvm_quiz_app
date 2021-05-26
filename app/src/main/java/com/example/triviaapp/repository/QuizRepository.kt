package com.example.triviaapp.repository

import com.example.triviaapp.db.Quiz
import com.example.triviaapp.db.QuizDatabase

class QuizRepository(private val quizDb: QuizDatabase) {


    suspend fun addQuiz(quiz: Quiz) = quizDb.getQuizDao().addQuiz(quiz)
    suspend fun updateQuiz(quiz: Quiz) = quizDb.getQuizDao().updateQuiz(quiz)
    suspend fun deleteQuiz(quiz: Quiz) = quizDb.getQuizDao().deleteQuiz(quiz)
    suspend fun getAllQuiz() = quizDb.getQuizDao().getAllQuizList()
    //suspend fun addMultipleQuiz(quiz: Quiz) = quizDb.addMultipleQuiz(quiz,quiz,quiz)
}