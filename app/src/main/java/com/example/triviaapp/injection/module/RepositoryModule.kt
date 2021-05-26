package com.example.triviaapp.injection.module

import com.example.triviaapp.db.QuizDatabase
import com.example.triviaapp.repository.QuizRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabaseRepo(quizDatabase: QuizDatabase): QuizRepository =
        QuizRepository(quizDatabase)


}