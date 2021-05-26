package com.example.triviaapp.db

import androidx.room.*


@Dao
interface QuizDao {

    @Insert
    suspend fun addQuiz(quiz: Quiz)

    @Update
    suspend fun updateQuiz(quiz: Quiz)

    @Delete
    suspend fun deleteQuiz(quiz: Quiz)

    // @Insert
    //suspend fun addMultipleQuiz(vararg quiz: Quiz)

    @Query("SELECT * FROM quiz ORDER BY id ASC ")
    suspend fun getAllQuizList(): List<Quiz>
}